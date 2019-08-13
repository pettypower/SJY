<template>
  <div>
    <common-header buyList='on'></common-header>
    <div style='height: 200px;'>
      <div class="searchBox">
        <div class="cur-place">您当前的位置：<a href="/cn">首页</a> &gt; <a href="/cn/buy" class="buy-list-name">二手货车</a></div>
        <div class="crumbs">
           <i></i>蜗牛在<span class="ss-city">全国</span>为您找到二手货车<span class="ss-brand-seric"></span> <em class="car-namber timer count-title" id="count-number" data-to="4817" data-speed="1500">4,817</em> 辆
        </div>
        <div class="search-filter-wrapper" rrc-event-scope="filter">
            <div class="container">
                <div class="clearfix sub-filter first" rrc-event-scope="brand">
                    <span class="filter-title">品&nbsp;牌：</span>
                    <div class="filter-list">
                        <ul id="filter_brand" class="">
                            <li class="active"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="" id="pc_search_filter_brand_all_brand" rrc-event-name="不限" rrc-event-param="search_bd_other">不限</a>
                            </li>
                            <li class="" data-value="26"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_ouman" rrc-event-name="福田欧曼" rrc-event-param="search_sg_pz">福田欧曼</a>
                            </li>
                            <li class="" data-value="40"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_shanqi" rrc-event-name="陕汽" rrc-event-param="search_sg_pz">陕汽</a></li>
                            <li class="" data-value="50"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_zhongqi" rrc-event-name="中国重汽" rrc-event-param="search_sg_pz">中国重汽</a>
                            </li>
                            <li class="" data-value="47"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_jiefang" rrc-event-name="一汽解放" rrc-event-param="search_sg_pz">一汽解放</a>
                            </li>
                            <li class="" data-value="22"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dongfeng" rrc-event-name="东风" rrc-event-param="search_sg_pz">东风</a></li>
                            <li class="" data-value="41"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_hongyan" rrc-event-name="上汽红岩" rrc-event-param="search_sg_pz">上汽红岩</a>
                            </li>
                            <li class="" data-value="29"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_jianghui" rrc-event-name="江淮" rrc-event-param="search_sg_pz">江淮</a></li>
                            <li class="" data-value="48"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_liute" rrc-event-name="柳特" rrc-event-param="search_sg_pz">柳特</a></li>
                            <li class="" data-value="35"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_lianhe" rrc-event-name="联合卡车" rrc-event-param="search_sg_pz">联合卡车</a>
                            </li>
                            <li class="" data-value="28"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_hualin" rrc-event-name="华菱" rrc-event-param="search_sg_pz">华菱</a></li>
                            <li class="" data-value="15"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_beibeng" rrc-event-name="北奔" rrc-event-param="search_sg_pz">北奔</a></li>
                            <li class="" data-value="19"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dayun" rrc-event-name="大运" rrc-event-param="search_sg_pz">大运</a></li>
                            <li class="" data-value="46"><a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_xugong" rrc-event-name="徐工汽车" rrc-event-param="search_sg_pz">徐工汽车</a>
                            </li>
                        </ul>
                    </div>
                    <div class="brand-more" id="brand_more" rrc-event-scope="more">
                        <a href="javascript:void(0)" class="brand-more-btn">更多

                            <span class="tra-1"></span>

                        </a>
                    </div>
                </div>
                <div class="clearfix sub-filter " rrc-event-scope="series">
                    <span class="filter-title">车&nbsp;系：</span>
                    <div class="filter-list">
                        <ul id="filter_series" class="overflow-hidden">
                            <li class=""><a href="javascript:void(0);" class="js-filter" data-key="series" data-value="" id="pc_search_filter_series_all_series" rrc-event-name="不限" rrc-event-param="search_bd_other">不限</a>
                            </li>
                        <li class="" data-value="95"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="J6" rrc-event-param="search_sg_pz">J6</a> </li><li class="" data-value="253"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="GTL" rrc-event-param="search_sg_pz">GTL</a> </li><li class="" data-value="110"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="豪运" rrc-event-param="search_sg_pz">豪运</a> </li><li class="" data-value="25"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="天龙" rrc-event-param="search_sg_pz">天龙</a> </li><li class="" data-value="69"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="德龙M3000" rrc-event-param="search_sg_pz">德龙M3000</a> </li><li class="" data-value="41"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="格尔发" rrc-event-param="search_sg_pz">格尔发</a> </li><li class="" data-value="117"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="悍威" rrc-event-param="search_sg_pz">悍威</a> </li><li class="" data-value="72"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="杰狮" rrc-event-param="search_sg_pz">杰狮</a> </li><li class="" data-value="100"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="V3" rrc-event-param="search_sg_pz">V3</a> </li></ul>
                    </div>
                </div>
                <div class="clearfix sub-filter model-box-div" rrc-event-scope="model">
                    <span class="filter-title">车&nbsp;型：</span>
                    <div class="filter-list">
                        <ul id="filter_model" class="overflow-hidden">
                            <li class=""><a href="javascript:void(0);" class="js-filter" data-key="series" data-value="" id="pc_search_filter_series_all_motor" rrc-event-name="不限" rrc-event-param="search_bd_other">不限</a>
                            </li>
                        <li class="" data-value="4"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="牵引车" rrc-event-param="search_sg_pz">牵引车</a> </li><li class="" data-value="6"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="载货车" rrc-event-param="search_sg_pz">载货车</a> </li><li class="" data-value="12"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="挂车" rrc-event-param="search_sg_pz">挂车</a> </li><li class="" data-value="13"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="自卸车" rrc-event-param="search_sg_pz">自卸车</a> </li><li class="" data-value="7"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="混凝土搅拌车" rrc-event-param="search_sg_pz">混凝土搅拌车</a> </li><li class="" data-value="15"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="散装物料车" rrc-event-param="search_sg_pz">散装物料车</a> </li><li class="" data-value="16"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="油罐车" rrc-event-param="search_sg_pz">油罐车</a> </li><li class="" data-value="17"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="冷藏车" rrc-event-param="search_sg_pz">冷藏车</a> </li><li class="" data-value="14"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="其他车型" rrc-event-param="search_sg_pz">其他车型</a> </li><li class="" data-value="18"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="洒水车" rrc-event-param="search_sg_pz">洒水车</a> </li><li class="" data-value="19"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="吊车" rrc-event-param="search_sg_pz">吊车</a> </li><li class="" data-value="20"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="混凝土泵车" rrc-event-param="search_sg_pz">混凝土泵车</a> </li></ul>
                        <div class="box-size" id="box-size" style="display: none;">
                            <p>箱体长度</p>
                            <ol class="box-size-ol">
                                <li data-num="54"><span>5.4</span></li>
                                <li data-num="56"><span>5.6</span></li>
                                <li data-num="58"><span>5.8</span></li>
                                <li data-num="60"><span>6.0</span></li>
                                <li data-num="62"><span>6.2</span></li>
                                <li data-num="68"><span>6.8</span></li>
                                <li data-num="72"><span>7.2</span></li>
                                <li data-num="76"><span>7.6</span></li>
                                <li data-num="78"><span>7.8</span></li>
                                <li data-num="80"><span>8.0</span></li>
                                <li data-num="82"><span>8.2</span></li>
                                <li data-num="86"><span>8.6</span></li>
                            </ol>
                            <p class="p">（米）</p>
                            <button class="shaixuan-css box-size-btn">
                                筛选
                            </button>
                        </div>
                        <div class="box-volume" style="display: none;">

                            <p>罐体容积</p>
                            <div class="volume-befor">
                                <input type="text" class="volume-befor-input price_box1">
                                (M<sup>3</sup>)
                            </div>
                            <div class="to">-</div>
                            <div class="volume-after">
                                <input type="text" class="volume-after-input price_box1">
                                (M<sup>3</sup>)
                            </div>
                            <button class="shaixuan-css box-volume-btn">
                                筛选
                            </button>
                        </div>
                    </div>
                </div>
                <div class="clearfix sub-filter" rrc-event-scope="price">
                    <span class="filter-title">价&nbsp;格：</span>
                    <div class="filter-list">
                        <ul id="filter_price" class="">
                            <li class=""><a href="javascript:void(0);" class="js-filter" data-key="pr" data-value="" id="pc_search_filter_price_all_brand" rrc-event-name="不限" rrc-event-param="search_bd_other">不限</a>
                            </li>
                        <li class="" data-value="1"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="1-10万元" rrc-event-param="search_sg_pz">1-10万元</a> </li><li class="" data-value="2"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="10-15万元" rrc-event-param="search_sg_pz">10-15万元</a> </li><li class="" data-value="3"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="15-20万元" rrc-event-param="search_sg_pz">15-20万元</a> </li><li class="" data-value="6"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="20-30万元" rrc-event-param="search_sg_pz">20-30万元</a> </li><li class="" data-value="4"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="30-50万元" rrc-event-param="search_sg_pz">30-50万元</a> </li><li class="" data-value="5"> <a href="javascript:void(0);" class="js-filter" data-key="brand" data-value="dazhong" id="pc_search_filter_brand_dazhong" rrc-event-name="50万元以上" rrc-event-param="search_sg_pz">50万元以上</a> </li></ul>
                        <div class="filter_price_box_user" data-val="wan">
                            <div class="price_box_div">

                                <input type="number" class="price_box1" id="filter_price_input1" rrc-event-name="inputboxleft" min="0" max="100">万
                            </div>
                            <div class="to">
                                -
                            </div>
                            <div class="price_box_div">
                                <input type="number" class="price_box1" id="filter_price_input2" rrc-event-name="inputboxright" min="0" max="100">万
                            </div>
                            <div id="filter_price_submit" class="btn-base btn-primary btn-sm" rrc-event-name="confirm">
                                筛选
                            </div>
                            </div>
                    </div>
                </div>
                <div class="clearfix sub-filter sub-filter__more">
                    <ul>
                        <span class="filter-title">其&nbsp;它：</span>
                        <li id="filter_level" class="js-dropdown sort-filter sort-filter__level" rrc-event-scope="level">
                            <div class="sort-title emission">
                                <em>排放标准</em>
                                <span class="arrow arrow-bottom"></span>
                                <span class="arrow arrow-top"></span>
                            </div>
                        </li>
                        <li id="filter_age1" class="js-dropdown sort-filter sort-filter__age" rrc-event-scope="age">
                            <div class="sort-title mileage">
                                <em>行驶里程</em>
                                <span class="arrow arrow-bottom"></span>
                                <span class="arrow arrow-top"></span>
                            </div>
                        </li>
                        <li id="" class="js-dropdown sort-filter sort-filter_carAge" rrc-event-scope="carAge">
                            <div class="sort-title carAge">
                                <em>按照车龄</em>
                                <span class="arrow arrow-bottom"></span>
                                <span class="arrow arrow-top"></span>
                            </div>
                        </li>
                        <li class="js-dropdown sort-filter sort-filter_sort" rrc-event-scope="sort">
                            <div class="sort-title sort-box">
                                <em>排序</em>
                                <span class="arrow arrow-bottom"></span>
                                <span class="arrow arrow-top"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                </div>
            <p class="condition" style="display: none;"><em>请选择你要筛选的条件：</em>     </p><p class="reset" style="display: none;">重置条件</p>
            <!--<span>红色<i></i></span><span>红色<i></i></span><span>红色<i></i></span>-->
        </div>
        <div id="city-chose" class="header" style="display: none;">
            <span class="filter-title">城&nbsp;市：</span>
            <div class="city"></div>
        </div>

    </div>
    </div>
    <common-footer></common-footer>
  </div>
</template>

<script>
// 引入公共的头部底部
import CommonHeader from './commonHead'
import CommonFooter from './commonFoot'

export default {
  name: 'buy',
  components: {
    CommonHeader,
    CommonFooter
  },
  props: {},
  data() {
    const that = this
    return {
      imgIndex: 1,
      swiperOption: {
        // 是一个组件自有属性，如果notNextTick设置为true，组件则不会通过NextTick来实例化swiper，也就意味着你可以在第一时间获取到swiper对象，假如你需要刚加载遍使用获取swiper对象来做什么事，那么这个属性一定要是true
        notNextTick: true,
        // 循环
        loop: true,
        // 设定初始化时slide的索引
        initialSlide: 0,
        // 自动播放
        autoplay: {
          delay: 1500,
          stopOnLastSlide: false,
          /* 触摸滑动后是否继续轮播 */
          disableOnInteraction: false
        },
        // 滑动速度
        speed: 800,
        // 滑动方向
        direction: 'horizontal',
        // 小手掌抓取滑动
        grabCursor: true,
        on: {
          // 滑动之后回调函数
          slideChangeTransitionStart: function() {
            /* realIndex为滚动到当前的slide索引值 */
            that.imgIndex = this.realIndex - 1
          }
        },
        // 分页器设置
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
          type: 'bullets'
        }
      },
      banner: [
        'http://qiniu-truck.woniuhuoche.com/20180918150612_Banner_15372543726816',
        'http://qiniu-truck.woniuhuoche.com/20180918150507_Banner_15372543070974'
      ],
      // 品牌
      brand: [
        {
          value: '50',
          img: '../../static/images/brand01.png'
        },
        {
          value: '47',
          img: '../../static/images/brand02.png'
        },
        {
          value: '26',
          img: '../../static/images/brand03.png'
        },
        {
          value: '40',
          img: '../../static/images/brand04.png'
        },
        {
          value: '22',
          img: '../../static/images/brand05.png'
        },
        {
          value: '29',
          img: '../../static/images/brand06.png'
        }
      ],
      // 品牌
      model: [
        {
          value: 'TRACTOR',
          img: '../../static/images/model01.png',
          carName: '牵引车'
        },
        {
          value: 'CARGO',
          img: '../../static/images/model02.png',
          carName: '载货车'
        },
        {
          value: 'TRAILER',
          img: '../../static/images/model06.png',
          carName: '挂车'
        },
        {
          value: 'DUMP',
          img: '../../static/images/model03.png',
          carName: '自卸车'
        },
        {
          value: 'MIXER_CONCRETE',
          img: '../../static/images/model04.png',
          carName: '混凝土搅拌车'
        },
        {
          value: 'BULK',
          img: '../../static/images/model05.png',
          carName: '散装物料车'
        }
      ],
      // 品牌
      prices: [
        {
          value: '1',
          price: '1-10万元'
        },
        {
          value: '2',
          price: '10-15万元'
        },
        {
          value: '3',
          price: '15-20万元'
        },
        {
          value: '4',
          price: '20-30万元'
        },
        {
          value: '5',
          price: '30-50万元'
        },
        {
          value: '6',
          price: '50万元以上'
        }
      ],
      cars: []
    }
  },
  computed: {},
  mounted() {},
  methods: {
    // 获取车辆信息方法
    getCars() {
      for (let i = 0; i < 8; i++) {
        this.cars.push({
          watermarkicon: '../../static/images/watermarkicon.png',
          img:
            'http://58.58.62.230:9077/20190801/2018112210115/f60c06b5-aedb-49d1-b855-b5947115c09e.jpg',
          carName: '东风商用天龙旗舰 牵引车  520匹 2017年12月 6x4',
          carMileage: '国五/',
          shge: '9.8',
          city: '济宁',
          price: '29.8',
          url: '/jn/170033.html?woniuUserid=0'
        })
      }
    }
  },
  created() {
    // 画面初始化默认加载信息
    this.getCars()
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style scoped>
  @import '../../static/css/buyList.css';
</style>
