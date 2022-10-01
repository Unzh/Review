package com.review.demo.interview;

import com.review.demo.interview.model.Transcript;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请用Java实现, 给定学生成绩数据:
 * <p>
 * | 课程名称 | 分数 | 姓名 |
 * | 语文    |    93 | n1   |
 * | 数学    |    85 | n1   |
 * | 英文    |    67 | n1   |
 * <p>
 * | 语文   |    73 | n2   |
 * | 数学    |    65 | n2   |
 * | 英文    |    97 | n2   |
 * <p>
 * | 语文  |    83 | n3   |
 * | 数学   |    87 | n3   |
 * | 英文    |    89 | n3   |
 * <p>
 * 求出各个科目下最高成绩的学生
 **/
public class Question4 {

    private static Map<String, List<Transcript>> mapCache = new HashMap<>();
    private static List<Transcript> transcriptList = null;

    public static void gather(List<Transcript> transcripts) {
        if (CollectionUtils.isEmpty(transcripts)) {
            return;
        }
        //方法一
        /*for(Transcript item:transcripts ){
            if (mapCache.get(item.getLesson())!=null&&mapCache.get(item.getLesson()).size() > 0) {
                if(item.getScore()>mapCache.get(item.getLesson()).get(0).getScore()){
                    transcriptList = new ArrayList<>();
                    transcriptList.add(item);
                    mapCache.put(item.getLesson(),transcriptList);
                }else if(item.getScore()==mapCache.get(item.getLesson()).get(0).getScore()){
                    transcriptList.add(item);
                    mapCache.put(item.getLesson(),transcriptList);
                }

            } else {
                transcriptList = new ArrayList<>();
                transcriptList.add(item);
                mapCache.put(item.getLesson(), transcriptList);
            }
        }*/

        transcripts.forEach(e->{
            if (mapCache.get(e.getLesson())!=null&&mapCache.get(e.getLesson()).size() > 0) {
                if(e.getScore()>mapCache.get(e.getLesson()).get(0).getScore()){
                    transcriptList = new ArrayList<>();
                    transcriptList.add(e);
                    mapCache.put(e.getLesson(),transcriptList);
                }else if(e.getScore()==mapCache.get(e.getLesson()).get(0).getScore()){
                    transcriptList.add(e);
                    mapCache.put(e.getLesson(),transcriptList);
                }
            } else {
                transcriptList = new ArrayList<>();
                transcriptList.add(e);
                mapCache.put(e.getLesson(), transcriptList);
            }
        });

    }

    public static void main(String[] args){
        /*List<Transcript> transcripts = new ArrayList<>();
        transcripts.add(new Transcript("语文",93,"n1"));
        transcripts.add(new Transcript("数学",85,"n1"));
        transcripts.add(new Transcript("英语",97,"n1"));
        transcripts.add(new Transcript("语文",73,"n2"));
        transcripts.add(new Transcript("数学",65,"n2"));
        transcripts.add(new Transcript("英语",97,"n2"));
        transcripts.add(new Transcript("语文",83,"n3"));
        transcripts.add(new Transcript("数学",87,"n3"));
        transcripts.add(new Transcript("英语",89,"n3"));
        Question4.gather(transcripts);
        System.out.println(mapCache);*/
        List<Map<String,String>> mapList = new ArrayList<>();
        for (int i=11;i<18;i++){
            Map<String,String> map = new HashMap<>();
            map.put("id",String.valueOf(i));
            mapList.add(map);
        }
        for(Map<String,String> map :mapList){
            map.put("url","444");
        }
        System.out.println(mapList);
    }

}
