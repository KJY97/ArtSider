<template>
  <div>
      <h1>공연 에티켓</h1>
<div class="showetiquette mb-4">
<v-btn
  elevation="2"
><router-link :to="{ name: 'musical' }"  style="text-decoration: none; color: inherit;"
>뮤지컬</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'theatre' }"  style="text-decoration: none; color: inherit;"
>연극</router-link></v-btn>
<v-btn
  elevation="2"
  color="warning"
><router-link :to="{ name: 'classic' }"  style="text-decoration: none; color: inherit;"
>클래식</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'opera' }"  style="text-decoration: none; color: inherit;"
>오페라</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'ktraditionalmusic' }"  style="text-decoration: none; color: inherit;"
>국악</router-link>
</v-btn>
</div>
    <v-img
    height="500px"
    width="500px"
    contain
        src="http://www.artinsight.co.kr/data/news/1907/3553982061_IXv6gSTW_151.jpg"
    ></v-img>

<!-- <v-btn
  elevation="2"
  color="warning"
></v-btn> -->
<!-- v-if로 에티켓 내용을 바꿔주기 -->
    <div class="etiquette-detail">
            <!-- 버튼 태그로 변경해주는게 나을듯 -->
        <!-- v-btn @click 이벤트  -->
        <!-- 클릭한 애들은 true 아닌애들은 false -->
        <!-- true면 보여주고 아니면 안보여주고 -->
        <!-- this.route로 path name 뽑아오기 가능 -->
        <!-- pathname으로 뽑아오는 부분 활용 -->
        <!-- btn에 대한 부분도 -->
        <!-- v-if 말고 v-show 사용 -->
    <div class="covid-19">
      <v-btn class="covid-19-btn"
      click
      >    
      <img src="https://cdn2.iconfinder.com/data/icons/coronavirus-covid-19-7/128/coronavirus_covid-19_disease_pandemic_epidemic_injury-256.png">
      </v-btn>
      <!-- <p v-show="covid-19" class="covid-19-ettie"> 코로나19 관련 방역지침 준수 -->
      <p class="covid-19-ettie"> 코로나19 관련 방역지침 준수
        발열체크, 마스크 착용 등 방역지침을 철저히 준수하여 모두가 안전한 공연관람이 되도록 합니다.</p>
    </div>

    <router-link :to="{ name: 'covid-19' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn2.iconfinder.com/data/icons/coronavirus-covid-19-7/128/coronavirus_covid-19_disease_pandemic_epidemic_injury-256.png">
    </router-link>
    <router-link :to="{ name: 'clap' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn1.iconfinder.com/data/icons/hand-gestures-color/128/hand-clap-y-256.png">
    </router-link>
    <router-link :to="{ name: 'food' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn4.iconfinder.com/data/icons/airport-and-travel-2/85/burger_hamburger_drink_soda_fast_food-256.png">
    </router-link>
    <router-link :to="{ name: 'phone' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn4.iconfinder.com/data/icons/prohibited-red-signs/122/Prohibition_sign_044-256.png">
    </router-link>
    <router-link :to="{ name: 'talk' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn2.iconfinder.com/data/icons/prohibited-forbidden-signs/100/Prohibited-23-256.png">
    </router-link>
    <router-link :to="{ name: 'time' }"  style="text-decoration: none; color: inherit;"
    ><img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/watch-256.png">
    </router-link>
    <!-- <a href="">
    <img src="https://cdn2.iconfinder.com/data/icons/prohibited-forbidden-signs/100/Prohibited-23-256.png">
    </a>
    <a href="">
    <img src="https://cdn1.iconfinder.com/data/icons/hand-gestures-color/128/hand-clap-y-256.png">
    </a>
    <a href="">
    <img src="https://cdn4.iconfinder.com/data/icons/prohibited-red-signs/122/Prohibition_sign_044-256.png">
    </a>
    <a href="">
    <img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/watch-256.png">
    </a> -->
    </div>
  </div>
  
</template>

<script>
// import KTraditionalMusic from './KTraditionalMusic.vue'

export default {
  name: 'Classic',
  components: {
  },
//   methods: {
//     covid-19 {

//     }
//   }
}
</script>

<style>
.covid-19 {
    margin-bottom: 500px;
    margin-left: 800px;
}

.covid-19-ettie {
    margin-top: 100px;
    /* margin-left: 800px; */
}
</style>