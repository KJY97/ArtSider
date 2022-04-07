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
        <h1 class="home-title" style="font-weight: 800; margin-bottom:20px;">
          아트사이더에 오신 걸 환영합니다.
        </h1>
      <v-btn
        class ="login-btn"
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
            class="signup-btn font-weight-bold"
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

    <PosterListUp 
      :open="listDialog"
      :posters="initPosters"
      @renew-poster="renewPoster()"
      @close-list="() => {deleteToken(), listDialog = false}"
    />
  
  </v-parallax>
</template>

<script>
import Login from '@/views/account/dialog/Login'
import SignUp from '@/views/account/dialog/SignUp'
import PosterListUp from '@/views/account/dialog/PosterListUp'

export default {
  name: 'Intro',
  components: {
    Login,
    SignUp,
    PosterListUp
  },

  data(){
    return{
      loginDialog: false,
      signUpDialog: false,
      listDialog: false,

      initPosters : [],
    }
  },

  methods:{
    deleteToken(){
      localStorage.removeItem('accessToken')
      this.$store.dispatch('deleteToken')
    },

    renewPoster(){
      this.$store.dispatch('getInitPoster')
      .then((res) => {
        this.initPosters = res.data.items.map((elem) => {
          const temp = {
              ...elem, 
              tags : res.data.tagList[elem.id].map((tag) =>{
                return tag.tag
            })
          }
          return temp
        })
      })
    }
  },

   beforeRouteLeave(to, from, next) {
    if(this.$store.state.userInfo.preferTag.length === 0){
      this.listDialog = true
      this.$store.dispatch('getInitPoster')
      .then((res) => {
        this.listDialog = true
        this.initPosters = res.data.items.map((elem) => {
          const temp = {
              ...elem, 
              tags : res.data.tagList[elem.id].map((tag) =>{
                return tag.tag
            })
          }
          return temp
        })
      })
      return false
    } else{
      next()
    }
  }
}
</script>

<style>
.sub-heading span:hover {
  background-color: rgba(255, 192, 203, 0.2);
}
</style>