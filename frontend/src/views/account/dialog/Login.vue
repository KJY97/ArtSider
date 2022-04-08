<template>
  <v-dialog
  :value="loginDialog"
  max-width="500px"
  @click:outside="() => { $emit('close-login') }"
  >
    <v-card style="padding: 3rem;">
      <v-card-title class="d-flex justify-center">
        <span class="text-h4"> 로그인 </span>
      </v-card-title>

      <v-container class="d-flex flex-column justify-center align-center" style="padding-bottom:50px">
        <span style="font-weight: 600; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;"> 아트사이더의 일원이 되어 보세요 </span>
        <div>
          <v-text-field
            style="width:400px;"
            v-model="userEmail"
            label="이메일"
            required
          ></v-text-field>


          <v-text-field
            style="width:400px;"
            v-model="userPassword"
            :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :type="passwordShow ? 'text' : 'password'"
            label="비밀번호"
            counter
            required
            @click:append="passwordShow = !passwordShow"
          ></v-text-field>
          <div
            class="other-options-wrapper"
          >
            <p 
              class="option"
              @click="passwordSearchDialog = true"
            >
            비밀번호 찾기 </p>

          </div>
          <v-btn
            outlined
            width="400px"
            @click="() => { getToken()}"
          >
            로그인
          </v-btn>
        </div>
      </v-container>
    </v-card>

    <PasswordSearch 
      :open="passwordSearchDialog"
      @close-password-search="() => { passwordSearchDialog = false }"
    />

  </v-dialog>
</template>

<script>
import PasswordSearch from './PasswordSearch'

export default { 
  name:'Login',
  props:{
    open : Boolean
  },
  components:{
    PasswordSearch
  },

  data(){
    return{
      signUpDialog: false,
      passwordSearchDialog: false,

      userEmail: '',
      userPassword: '',
      passwordShow: false
    }
  },

  methods:{
    getToken(){
        this.$store.dispatch('getToken', {userEmail: this.userEmail, password: this.userPassword})
    },

  },

  computed:{
    loginDialog(){
      return this.open ? true : false
    }
  }

}
</script>

<style>
.option{
  display: inline-block;
  cursor: pointer;
}

</style>