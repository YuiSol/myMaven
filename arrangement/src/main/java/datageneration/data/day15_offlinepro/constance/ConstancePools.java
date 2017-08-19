package datageneration.data.day15_offlinepro.constance;

/**
 * Created by tourbis on 2017/8/10.
 * 首页(内容页)点入即到详情页
 * 导航页点入到内容页，再次点击可到详情页
 */
public interface ConstancePools {
    String HOST_NAME="http://blog.163.com/";
    String SUFFIX_NAME_PHOTO="photo";
    String SUFFIX_NAME_COMMUNITY="community";
    String SUFFIX_NAME_CULTURE="culture";
    String SUFFIX_NAME_LIFE="life";
    String ERROR_FLAG="error_404";
    String ENCODE="UTF-8";

    //TODO 首页内容 homepage ---详情页(作者，文章，详情页映射)
    String HOME_PAGE=HOST_NAME.concat("homepage/");

    //TODO 导航页---图片内容页  pic ---详情页(作者，文章，详情页映射)
    String NAVIGATION_PHOTO=HOST_NAME.concat("navigation/").concat("photo/");

    //TODO 导航页---实事内容页  comment ---详情页(作者，文章，详情页映射)
    String NAVIGATION_COMMUNITY=HOST_NAME.concat("navigation/").concat("comment/");

    //TODO 导航页---文化内容页  culture ---详情页(作者，文章，详情页映射)
    String NAVIGATION_CULTURE=HOST_NAME.concat("navigation/").concat("culture/");

    //TODO 导航页---生活内容页 life ---详情页(作者，文章，详情页映射)
    String NAVIGATION_LIFE=HOST_NAME.concat("navigation/").concat("life/");

    String ERROR_PAGE=HOST_NAME.concat(ERROR_FLAG);

    String USER_AGENT[] =
            {
             //-----------------------------------------------PC端-----------------------------------------------
             //safari 5.1 – MAC
            "Mozilla/5.0(Macintosh;U;Intel Mac OS X 10_6_8;en-us)AppleWebKit/534.50(KHTML,like Gecko)Version/5.1Safari/534.50",
             //safari 5.1 – Windows
            "Mozilla/5.0(Windows; U;Windows NT 6.1;en-us)AppleWebKit/534.50(KHTML,like Gecko)Version/5.1Safari/534.50",
            //IE 9.0
            "Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)",
            //IE 8.0
            "Mozilla/4.0(compatible;MSIE 8.0;Windows NT 6.0;Trident/4.0)",
            //IE 7.0
            "Mozilla/4.0(compatible;MSIE 7.0;Windows NT 6.0)",
            //IE 6.0
            "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1)",
            //Maxthon 遨游
            "Mozilla/4.0(compatible;MSIE 7.0;Windows NT 5.1;Maxthon 2.0)",
            //腾讯
            "Mozilla/4.0(compatible;MSIE 7.0;Windows NT 5.1;TencentTraveler 4.0)",
            //IE 8.0
            "Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)",
            //Firefox 4.0.1 – MAC
            "Mozilla/5.0(Macintosh;Intel Mac OS X 10.6;rv:2.0.1)Gecko/20100101Firefox/4.0.1",
            //Firefox 4.0.1 – Windows
            "Mozilla/5.0(Windows NT 6.1; rv:2.0.1)Gecko/20100101Firefox/4.0.1",
            //Opera 11.11 – MAC
            "Opera/9.80(Macintosh; Intel Mac OS X 10.6.8; U; en)Presto/2.8.131Version/11.11",
            //Opera 11.11 – Windows
            "Opera/9.80(Windows NT 6.1; U; en)Presto/2.8.131Version/11.11",
            //Chrome 17.0 – MAC
            "Mozilla/5.0(Macintosh; Intel Mac OS X 10_7_0)AppleWebKit/535.11(KHTML, like Gecko)Chrome/17.0.963.56 Safari/535.11",
            //---------------------------------------------------移动端---------------------------------------------------

            //Android Opera Mobile
            "Opera/9.80(Android 2.3.4; Linux; Opera Mobi/build-1107180945;U; en-GB)Presto/2.8.149 Version/11.10",
            //WebOS HP Touchpad
            "Mozilla/5.0(hp-tablet; Linux; hpwOS/3.0.0; U; en-US)AppleWebKit/534.6(KHTML, like Gecko)wOSBrowser/233.70 Safari/534.6 TouchPad/1.0",
            //safari iOS 4.33 – iPad
            "Mozilla/5.0(iPad; U; CPU OS 4_3_3 like Mac OS X; en-us)AppleWebKit/533.17.9(KHTML, like Gecko)Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            //safari iOS 4.33 – iPod Touch
            "Mozilla/5.0(iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us)AppleWebKit/533.17.9(KHTML, like Gecko)Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            //UC无
            "UCWEB7.0.2.37/28/999",
             //UC标准
            "NOKIA5700/UCWEB7.0.2.37/28/999",
            //UCOpenwave
            "Openwave/UCWEB7.0.2.37/28/999",
            //UC Opera
            "Mozilla/4.0(compatible; MSIE 6.0; )Opera/UCWEB7.0.2.37/28/999"
            };
    @Deprecated
    enum UserAgentInfo{
        SAFARI5_1_MAX,SAFARI5_1_WINDOWS,IE9_0,IE_8_0,IE_7_0,IE_6_0,FIREFOX4_0_1_MAC,
        FIREFOX_4_0_1_WINDOWS,OPERA_MAC,OPERA_WINDOWS,CHROME_MAC,MAXTHON,TT,THE_WORLD,
        GREEN_BROWSER
    }

    String REQUEST_STARTS[]={"200","403","failed"};
}
