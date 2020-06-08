package com.review.demo.SimpSpring.AOP.IOC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的IOC--容器类
 */
public class SimpleIOC {
    private Map<String, Object> beanMap = new HashMap<>();

    public SimpleIOC(String location) throws Exception {
        loadBean(location);
    }

    public Object getBean(String name) {
        Object bean = new Object();
        if (bean == null) {
            throw new IllegalArgumentException("there is no bean with name " + name);
        }
        return bean;
    }

    /**
     * 通过加载xml文件方式
     *
     * @param location
     * @throws Exception
     */
    private void loadBean(String location) throws Exception {
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        //遍历 <bean>
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                //加载 class
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                /**
                 * 创建bean
                 */
                Object bean = beanClass.newInstance();

                /**
                 * 遍历property 标签
                 */
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); i++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");

                        //利用反射将bean 相关字段设置成 可访问
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            //将属性填充到相关字段中
                            declaredField.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == "" || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            // 将引用填充到相关字段中
                            declaredField.set(bean, getBean(ref));
                        }

                        //将bean 注册到bean容器中
                        registerBean(id, bean);

                    }
                }
            }
        }
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }
}
