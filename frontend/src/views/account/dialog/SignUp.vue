<template>
  <v-dialog
    :value="signUpDialog"
    dark
    max-width="500px"
    @click:outside="() => { $emit('close-sign-up') }"
  >
    
  <v-card>
      <v-card-title>
        <span class="text-h2"> 회원가입 </span>
      </v-card-title>
      <v-container>
        <v-form
          ref="form"
          v-model="valid"
          lazy-validation 
          class="sign-form"
        >
          <v-text-field
            v-model="userName"
            :rules="userNameRules"
            label="닉네임"
            required
          ></v-text-field>

          <v-text-field
            v-model="userEmail"
            :rules="userEmailRules"
            label="이메일"
            required
          ></v-text-field>


          <v-text-field
            v-model="userPassword"
            :rules="userPasswordRules"
            :append-icon="passwordShow ? 'mdi-eye' : 'mdi-eye-off'"
            :type="passwordShow ? 'text' : 'password'"
            label="비밀번호"
            counter
            required
            @click:append="passwordShow = !passwordShow"
          ></v-text-field>

          <v-text-field
            v-model="userPasswordConfirmation"
            :rules="userPasswordConfirmationRules"
            label="비밀번호 확인"
            type="password"
            counter
            required
          ></v-text-field>

          <v-text-field
            v-model="userTelNum"
            :rules="userTelNumRules"
            label="전화번호"
            type="tel"
            required
          ></v-text-field>

          
        </v-form>
        <v-card-actions
          class="px-0"
        >
          <v-btn 
          outlined
          :disabled="!valid"
          @click="submitSignUpForm"
          width="100%"
          >
            회원가입
          </v-btn>
        </v-card-actions>
      </v-container>
    </v-card>
    
    <EmailConfirmation
    :userData = userData
    :confirmDialog = confirmDialog
    @close-dialog="() => { confirmDialog = false, $emit('close-sign-up') }"
    ></EmailConfirmation>

  </v-dialog>
</template>

<script>
// import $axios from 'axios'
import EmailConfirmation from '@/views/account/dialog/EmailConfirmation'

export default {
  name: 'SignUp',
  components:{
    EmailConfirmation
  },

  props:{
    open : Boolean
  },
  data(){
    return{
      userData : {},
      confirmDialog : false,
      valid: true,
      passwordShow : false,
      userName: '',
      userNameRules: [
        v => v.trim() !== ''  || '닉네임을 입력해주세요.',
        v =>  !/[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/g.test(v) || '특수문자는 입력하실 수 없습니다.'
      ],
      userEmail: '',
      userEmailRules: [
        v => v.trim() !== '' || '이메일을 입력해주세요.',
        v => /^([\w._-])*[a-zA-Z0-9]+([\w._-])*([a-zA-Z0-9])+([\w._-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,}$/.test(v) || '유효한 이메일을 입력해주세요.'
      ],

      userPassword: '',
      userPasswordRules: [
        v => v.trim() !== '' || '비밀번호를 입력해주세요.',
        v => v.replaceAll(' ', '').length >= 9 && v.replaceAll(' ', '').length <= 16 || '9자 이상 16자 이하로 입력해주세요.',
        v => /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{9,}$/.test(v) || '영어 대소문자, 숫자, 특수문자를 포함해야 합니다.'
      ],

      userPasswordConfirmation: '',
      userPasswordConfirmationRules: [
        v => v.trim() !== '' || '비밀번호를 입력해주세요.',
        v => v === this.userPassword || '비밀번호가 일치하지 않습니다.'
      ],

      userTelNum: '',
      userTelNumRules: [
        v => v.trim() !== '' || '전화번호를 입력해주세요.',
        v => /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(v) || '휴대폰 번호만 입력해주세요'
      ]
    }
  },

  computed:{
    signUpDialog(){
      return this.open ? true : false
    }
  },

  methods:{
    submitSignUpForm(event){
      event.preventDefault()
      const validate =  this.$refs.form.validate()
      if (validate){
        // 유호성 통과
        this.userData = {
          'userName' : this.userName,
          'userPassword' : this.userPassword,
          'userEmail' : this.userEmail,
          'telNum' :  this.userTelNum.replaceAll('-', '')
        }
        this.confirmDialog = true
      }

    }
  },
}
</script>

<style>

</style>