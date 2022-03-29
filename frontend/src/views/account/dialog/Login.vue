<template>
  <v-dialog
  :value="loginDialog"
  dark
  max-width="500px"
  @click:outside="() => { $emit('close-login') }"
  >
    <v-card>
      <v-card-title>
        <span class="text-h2"> 로그인 </span>
      </v-card-title>

      <v-container>
        <v-form>
          <v-text-field
            v-model="userEmail"
            label="이메일"
            required
          ></v-text-field>


          <v-text-field
            v-model="userPassword"
            :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :type="passwordShow ? 'text' : 'password'"
            label="비밀번호"
            counter
            required
            @click:append="passwordShow = !passwordShow"
          ></v-text-field>
        </v-form>
        <div
          class="other-options-wrapper"
        >
          <p 
            class="option"
            @click="passwordSearchDialog = true"
          >
           비밀번호 찾기 </p>
          <v-spacer></v-spacer>
          <p 
            class="option" 
            @click="signUpDialog = true"
          > 
            회원가입 
          </p>
        </div>
        <v-card-actions
        class="px-0"
        >
          <v-btn
            outlined
            width="100%"
            @click="login"
          >
            로그인
          </v-btn>
        </v-card-actions>
      </v-container>
    </v-card>

    <SignUp 
      :open="signUpDialog"
      @close-sign-up="() => { signUpDialog = false }"
    />

    <PasswordSearch 
      :open="passwordSearchDialog"
      @close-password-search="() => { passwordSearchDialog = false }"
    />

  </v-dialog>
</template>

<script>
import SignUp from './SignUp'
import PasswordSearch from './PasswordSearch'

export default { 
  name:'Login',
  props:{
    open : Boolean
  },
  components:{
    SignUp,
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
    login(){
        this.$store.dispatch()
    }
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