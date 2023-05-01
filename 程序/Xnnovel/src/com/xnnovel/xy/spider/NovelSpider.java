package com.xnnovel.xy.spider;


import com.xnnovel.xy.controller.NovelController;
import com.xnnovel.xy.utils.JsoupHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;


/**
 * @author：Mr.Yang
 * @date：2018/10/4 14:26
 */
public class NovelSpider {

    private static Logger logger = LoggerFactory.getLogger(NovelSpider.class);

    public static void novelSpider(String url,String xpath){

        try {
            String typeurl = JsoupHelper.fecthAttr(url,xpath,"href").get(0);
            //小说类型
            Integer type = Integer.parseInt(typeurl.substring(typeurl.lastIndexOf("/")+1,typeurl.indexOf("_")));
            Object result = JsoupHelper.fecthNode("https://www.23us.so/xiaoshuo/13694.html","//*[@id='at']/tbody/tr/td");
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node == null) {
                        continue;
                    }
                    logger.info("text:{}",node.getTextContent());
                    logger.info("nodeValue:{}",node.getLastChild().getNodeValue());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {


        novelSpider("https://www.23us.so/xiaoshuo/13694.html","//*[@id='at']/tbody/tr/td");
    }
}
