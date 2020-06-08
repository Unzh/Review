package com.review.demo.Utils;

import java.util.ArrayList;

/**
 * 树状结构对象的存储实现类
 */
public class TreeNode {

    private Long id;
    private String name;
    private Integer level;
    private Long parentId;
    private Boolean leaf = Boolean.TRUE;

    private ArrayList<TreeNode> childrenList = new ArrayList<>();
    private TreeNode parent;

    public TreeNode(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TreeNode(Long id, String name, Boolean leaf) {
        this.id = id;
        this.name = name;
        this.leaf = leaf;
    }

    /**
     * 添加孩子结点
     */
    public void addChild(TreeNode children) {
        if (children == null || children.getId().longValue() == id.longValue()) {
            return;
        }
        childrenList.add(children);
        children.setParent(this);
        children.setParentId(this.id);
    }

    /**
     * 删除一个孩子结点
     */
    public void delChild(Long childId) {
        if (childId == null || childId.longValue() == id.longValue()) {
            return;
        }
        for (TreeNode node : childrenList) {
            if (node.getId().longValue() == childId.longValue()) {
                childrenList.remove(node);
                node.setParent(null);
                node = null;
                break;
            } else {
                node.delChild(childId);
            }
        }
    }

    /**
     * 得到根节点
     *
     * @return
     */
    public TreeNode getRoot() {
        if (getParent() == null) {
            return this;
        } else {
            return getParent().getRoot();
        }
    }

    /**
     * 根据Id查找结点
     *
     * @return
     */
    public TreeNode getChild(Long id) {
        if (id == null) {
            return null;
        }
        for (TreeNode node : childrenList) {
            if (node.getId().longValue() == id.longValue()) {
                return node;
            } else {
                TreeNode temp = node.getChild(node.getId());
                if (temp != null) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * 从给定的节点开始向下遍历，并调用回调函数对每个节点进行操作。
     *
     * @return
     */
    public void eachAllChild(TreeNode root, CallBack callBack) {
        callBack.execute(root);
        for (TreeNode node : childrenList) {
            eachAllChild(node,callBack);
        }
    }

    /**
     * 遍历树节点时使用的回调方法。可在遍历的过程中对节点进行操作。
     *
     */
    public interface CallBack {
        void execute(TreeNode node);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public ArrayList<TreeNode> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<TreeNode> childrenList) {
        this.childrenList = childrenList;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
