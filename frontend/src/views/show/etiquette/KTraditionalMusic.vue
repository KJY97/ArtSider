<template>
  <div>
      <h1>공연 에티켓</h1>
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
><router-link :to="{ name: 'classic' }"  style="text-decoration: none; color: inherit;"
>클래식</router-link></v-btn>
<v-btn
  elevation="2"
><router-link :to="{ name: 'opera' }"  style="text-decoration: none; color: inherit;"
>오페라</router-link></v-btn>
<v-btn
  elevation="2"
  color="warning"
><router-link :to="{ name: 'ktraditionalmusic' }"  style="text-decoration: none; color: inherit;"
>국악</router-link>
</v-btn>

    <v-img
    height="500px"
    width="500px"
        src="https://mblogthumb-phinf.pstatic.net/MjAxNzEwMjRfNDEg/MDAxNTA4ODIyMDYwMjUy.RKvYx7WgXNYaLG7Qd9K04vBQ3iA2kEkpIGrGbwtMLw4g.7qQpAZGP6IJ73prc8bm6EjXsNp6Dpmy7o-xIMBEfebkg.PNG.gugak1951/%EA%B5%AD%EC%95%85_4.png?type=w800"
    ></v-img>

<v-btn
  elevation="2"
  color="warning"
></v-btn>

    <div class="etiquette-detail">
            <!-- 버튼 태그로 변경해주는게 나을듯 -->
        <!-- v-btn @click 이벤트  -->
        <!-- 클릭한 애들은 true 아닌애들은 false -->
        <!-- true면 보여주고 아니면 안보여주고 -->
        <!-- this.route로 path name 뽑아오기 가능 -->
        <!-- pathname으로 뽑아오는 부분 활용 -->
        <!-- btn에 대한 부분도 -->
        <!-- v-if 말고 v-show 사용 -->
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
    </div>
    </div>
  
</template>

<script>
export default {
  name: 'Classic',
  components: {
  }
}
</script>

<style>

</style>