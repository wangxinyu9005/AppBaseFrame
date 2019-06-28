package com.xinbo.app.appbaselibrary.constants;

/**
 * @description 与后台接口调试url
 * @date 2019年04月09日14:08:43
 * @author wxy
 */
public class Urls {
    /************************************健康镜子****************************************************/
    //服务器地址
    public static final String BASE_URL="http://ai.ctshangyi.com:8080";
    private static final String baseUrl =BASE_URL+"/IrMirrorProductService/mvc/v1.1/";

    /*******************************************数字家庭医生(草堂上医)*********************************/

    /**
     * 服务器地址
     * 版本:v1.2
     */
    public static final String CTSY_BASE = "http://fm.ctshangyi.com/jkww/mvc/v1.2";//草堂上医服务器
    // 获取生活指数
    public static final String HOME_LIFE_INDEX = CTSY_BASE + "/qxyx/getMjtqshzs";
}
