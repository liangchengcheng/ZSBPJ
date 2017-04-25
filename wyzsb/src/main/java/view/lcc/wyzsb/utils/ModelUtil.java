package view.lcc.wyzsb.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import view.lcc.wyzsb.R;
import view.lcc.wyzsb.bean.Video;
import view.lcc.wyzsb.bean.model.ChannelEntity;
import view.lcc.wyzsb.bean.model.FilterEntity;
import view.lcc.wyzsb.bean.model.FilterTwoEntity;
import view.lcc.wyzsb.bean.model.OperationEntity;
import view.lcc.wyzsb.bean.model.TravelingEntity;


/**
 * 好吧，让你找到了，这是假的数据源
 * <p>
 * Created by sunfusheng on 16/4/22.
 */
public class ModelUtil {

    public static final String type_scenery = "资讯";
    public static final String type_building = "大事件";
    public static final String type_animal = "招聘";
    public static final String type_plant = "其他";

    /**
     * 广告数据
     */
    public static List<String> getBannerData() {
        List<String> adList = new ArrayList<>();
        adList.add("http://img5.imgtn.bdimg.com/it/u=3387699877,572565760&fm=23&gp=0.jpg");
        adList.add("http://img4.imgtn.bdimg.com/it/u=3585605996,2650481562&fm=23&gp=0.jpg");
        return adList;
    }

    /**
     * 频道数据
     */
    public static List<ChannelEntity> getChannelData() {
        List<ChannelEntity> channelList = new ArrayList<>();
        channelList.add(new ChannelEntity("WEB安全", "WordPress",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=304665959,1413891126&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("系统安全", "Linux",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=659929953,2220604144&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("网络安全", "IIS",
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3106582618,3548567193&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("终端安全", "路由器",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=8699978,3001483890&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("数据安全", "mysql",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1874647747,2949411964&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("内网渗透", "0day",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=306464908,2928118390&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("逆向破解", "Android",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=283337389,3090814806&fm=23&gp=0.jpg"));
        channelList.add(new ChannelEntity("漏洞分析", "审计",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=603488282,2345355477&fm=23&gp=0.jpg"));
        return channelList;
    }

    /**
     * 运营数据
     */
    public static List<OperationEntity> getOperationData() {
        List<OperationEntity> operationList = new ArrayList<>();
        operationList.add(new OperationEntity("零基础", "命令行/编程", R.drawable.biancheng));
        operationList.add(new OperationEntity("小工具", "安全方便", R.drawable.gongju));
        return operationList;
    }

    // 分类数据
    public static List<FilterTwoEntity> getCategoryData() {
        List<FilterTwoEntity> list = new ArrayList<>();
        list.add(new FilterTwoEntity(type_scenery, getFilterData()));
        list.add(new FilterTwoEntity(type_building, getFilterData()));
        list.add(new FilterTwoEntity(type_animal, getFilterData()));
        list.add(new FilterTwoEntity(type_plant, getFilterData()));
        return list;
    }

    // 排序数据（时间的顺序）
    public static List<FilterEntity> getSortData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("最新", "1"));
        list.add(new FilterEntity("最热", "2"));
        return list;
    }

    // 筛选数据 （按照类别分类）
    public static List<FilterEntity> getFilterData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("初级", "1"));
        list.add(new FilterEntity("中级", "2"));
        list.add(new FilterEntity("高级", "3"));
        return list;
    }



    /**
     * 暂无数据
     */
    public static List<Video> getNoDataEntity(int height) {
        List<Video> list = new ArrayList<>();
        Video entity = new Video();
        entity.setNoData(true);
        entity.setHeight(height);
        list.add(entity);
        return list;
    }

}