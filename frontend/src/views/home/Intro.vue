<template>
  <v-parallax
    dark
    src="../../assets/hall_image.svg"
    height="720"
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
        color="red"
        dark
        @click="() => { loginDialog = true }"
      >
        로그인 하러 가기
      </v-btn>
      <br>
      <br>
        <h4 class="sub-heading">
          회원이 아니신가요 ? 
          <v-btn
            class="font-weight-bold text-decoration-underline"
            text
            color="blue"
            plain
            @click="() => { signUpDialog = true }"            
          > 가입하기 </v-btn>
        </h4>
        <br>
        <hr style="width:400px" class="mx-auto">
        <br>
        <v-img
          class="mx-auto" 
          height="45px"
          width="183px"
          position="center center"
          src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png"
        ></v-img>
      </v-col>
    </v-row>

    <Login 
      :open="loginDialog"
      @close-login="() => { loginDialog = false }" 
    />

    <SignUp
      :open="signUpDialog"
      @close-sign-up="() => { signUpDialog = false }"
    />
  </v-parallax>
</template>

<script>
import Login from '@/views/account/dialog/Login'
import SignUp from '@/views/account/dialog/SignUp'

export default {
  name: 'Intro',
  components: {
    Login,
    SignUp
  },

  data(){
    return{
      loginDialog: false,
      signUpDialog: false
    }
  },

}
</script>

<style>
</style>