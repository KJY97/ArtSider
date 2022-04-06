<template>
  <v-parallax
    dark
    src="../../assets/intro.png"
    style="height:100vh;"
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
      <hr style="width:400px" class="mx-auto">
      <br>
        <p class="sub-heading" style="font-size:16px">
          회원이 아니신가요 ? 
          <v-btn
            text
            class="font-weight-bold"
            style="font-size:16px; color:rgb(30, 130, 197);"
            @click="() => { signUpDialog = true }"        
          > 가입하기 </v-btn>
        </p>
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