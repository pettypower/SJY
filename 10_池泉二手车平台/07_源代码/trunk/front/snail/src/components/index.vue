<template>
  <div>
    <common-header index="on"></common-header>
    <div>
      <!-- 图片轮播 -->
      <div class='slide-show'>
        <swiper :options='swiperOption' class='swiper-wrap' ref='mySwiper' v-if='banner.length!=0'>
          <swiper-slide v-for='(item,index) in banner' :key='index'>
            <img :src='item' alt @click="goRoute('/buy')"/>
          </swiper-slide>
          <!-- 常见的小圆点 -->
          <div style='margin-bottom:180px'
            class='swiper-pagination'
            v-for='(item,index) in banner'
            :key='index'
            slot='pagination'
          ></div>
        </swiper>
      </div>
      <div class='content'>
        <div class='wrap'>
          <div class='fast-entry'>
            <div class='buy-car'>
              <h2>
                我要买车
                <span></span>
              </h2>
              <p class='p'>全国区域覆盖，各种车源等您来挑</p>
              <div class='search'>
                <p>
                  <input
                    class='search-text on-txt'
                    type='text'
                    placeholder='请输入您感兴趣的车辆信息   如：品牌，车型等'
                    value
                  />
                </p>
                <span @click="goRoute('/buy')">
                  <i></i>搜索
                </span>
              </div>
              <div class='car-option'>
                <div class='option'>
                  <div class='option-wrap1'>
                    <ul>
                      <li style='width: auto;'>品牌</li>
                      <li :data-value='item.value' v-for='item in brand' :key='item.value'>
                        <img :src='item.img' alt  @click="goRoute('/buy')"/>
                      </li>
                      <li style='width: auto; cursor: pointer'>
                        <router-link style=' color: #999;'  tag='a' :to="'/buy'"> 更多
                          <img src='../../static/images/jiantou.png' alt />
                        </router-link>
                      </li>
                    </ul>
                  </div>
                  <div class='clear'></div>
                  <div class='models'>
                    <ul>
                      <li style='width: auto; margin-top: 30px;'>车型</li>
                      <li :data-value='item.value' v-for='item in model' :key='item.value'  @click="goRoute('/buy')">
                        <p class='pImg'>
                          <img :src='item.img' alt/>
                        </p>
                        <p>{{item.carName}}</p>
                      </li>
                      <li style='width: auto; margin-top: 30px;cursor: pointer'>
                       <router-link style=' color: #999;'  tag='a' :to="'/buy'"> 更多
                          <img src='../../static/images/jiantou.png' alt />
                        </router-link>
                      </li>
                    </ul>
                  </div>
                  <div class='clear'></div>
                  <div class='price'>
                    <ul>
                      <li style='width: auto;'>价格</li>
                      <li  @click="goRoute('/buy')" :data-value='item.value' v-for='item in prices' :key='item.value'>{{item.price}}</li>
                      <li style='width: auto;cursor: pointer'>
                        <router-link style=' color: #999;'  tag='a' :to="'/buy'"> 更多
                          <img src='../../static/images/jiantou.png' alt />
                        </router-link>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class='ad'>
            <img src alt />
          </div>
          <ul class='vehicle-list' id='vehicle-list'>
            <li v-for='(item, index) in cars' :key='index'>
              <a target='_blank' :href='item.url'>
                <div class='position'>
                  <i class='recentTag'></i>
                  <p class='list-img'>
                    <i class='markcent'>
                      <img :src='item.watermarkicon' />
                    </i>
                    <img
                      class='car-wrap'
                      :src='item.img'
                      :alt='item.carName'
                      :data-echo='item.img'
                      style='display: inline;'
                    />
                  </p>
                  <p class='car-name'>{{item.carName}}</p>
                  <p class='car-mileage'>
                    {{item.carMileage}}
                    <span class='shge'>{{item.shge}}</span>
                    万公里/{{item.city}}
                  </p>
                  <p class='car-price padd-14'>
                    <span class='price'>
                      <em>
                        <span class='pri'>{{item.price}}</span>万元
                      </em>
                      <em class='kucun color4'>可议价</em>
                    </span>
                  </p>
                </div>
              </a>
            </li>
          </ul>
          <div class='release'>
            <router-link tag='a' :to="'/buy'">
            </router-link>
          </div>
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
  name: 'index',
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
@import '../../static/css/index.css';
</style>
