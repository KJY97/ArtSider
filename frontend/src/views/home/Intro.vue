<template>
  <v-parallax
    dark
    src="../../assets/hall_image.svg"
    style="height:100vh"
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
          @click="kakaoLoginBtn"
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
  methods: {
    kakaoLoginBtn: function() {
      window.Kakao.init('193bfca2b0be2c8fecd92926dbcc7a1f') // Kakao Developers에서 요약 정보 -> JavaScript 키

      if (window.Kakao.Auth.getAccessToken()) {
        window.Kakao.API.request({
          url: '/v1/user/unlink',
          success: function (response) {
            console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
        window.Kakao.Auth.setAccessToken(undefined)
      }


      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: '/v2/user/me',
            data: {
              property_keys: ["kakao_account.email"]
            },
            success: async function (response) {
              console.log(response);
            },
            fail: function (error) {
              console.log(error)
            },
          })
        },
        fail: function (error) {
          console.log(error)
        },
      })
    }
  }
}
</script>

<style>
</style>