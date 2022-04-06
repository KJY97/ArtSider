<template>
  <v-parallax
    dark
    src="../../assets/intro3.png"
    style="height:100vh;"
  >
    <v-row
      align="center"
      justify="center"
    >
      <v-col
        class="text-center flex-column d-flex justify-center align-center"
        cols="12"
      >
        <v-img
        src="../../assets/123.svg"
        style="width:700px; margin-bottom:40px;"
        >

        </v-img>
        <h1 class="text-h3" style="font-weight: 800; margin-bottom:20px;">
          아트사이더에 오신걸 환영합니다.
        </h1>
      <v-btn
        color="red"
        dark
        @click="() => { loginDialog = true }"
      >
        로그인 하러 가기
      </v-btn>
      <br>
      <hr style="width:400px" class="mx-auto">
      <br>
        <p class="d-flex justify-center align-center sub-heading" style="font-size:16px;">
          회원이 아니신가요 ?
          <span
            text
            class="font-weight-bold"
            style="font-size:16px; color:pink; margin-left: 30px; cursor:pointer;"
            @click="() => { signUpDialog = true }"        
          >가입하기 </span>
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
.sub-heading span:hover {
  background-color: rgba(255, 192, 203, 0.2);
}
</style>