<template>
<!-- 로그인이 안되있을때 메인페이지 -->
<!-- navguard로 막는다 -->
  <v-parallax
    dark
    src="../../assets/hall_image.svg"
    height="970"
  >
    <v-row
      align="center"
      justify="center"
    >
      <v-col
        class="text-center"
        cols="12"
      >
        <h1 class="text-h4 font-weight-thin mb-4">
          ARTSIDER를 이용하시려면 로그인이 필요합니다
        </h1>
      <v-btn
        color="error"
        dark
        :loading="loading"
      >
        로그인 하러 가기
        <!-- a태그나 @click달아서 로그인페이지로 날려주기 -->
        <!-- 로그인 버튼 크기 카카오 로그인 버튼 크기랑 맞춰주기 -->
      </v-btn>
      <br>
      <br>
        <h4 class="subheading">
          회원이 아니신가요 ? <a href="">가입하기</a>
           <!-- 여기는 a태그로 해놨네 가입하기 페이지로 날려주기  -->
        </h4>
        <br>
        <hr style="width:400px" class="mx-auto">
        <br>
        <!-- v-img가 중앙 정렬 안되서 겁나 해멨는데 class="mx-auto" 주니까 해결 S -->
        <v-img
          class="mx-auto" 
          height="45px"
          width="183px"
          position="center center"
          src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png"
        ></v-img>
      </v-col>
    </v-row>
  </v-parallax>
</template>

<script>
export default {
  name: 'Intro',
  components: {
  }
}
</script>

<style>
</style>