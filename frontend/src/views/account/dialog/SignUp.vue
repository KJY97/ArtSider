<template>
  <v-dialog
    :value="signUpDialog"
    dark
    max-width="500px"
    @click:outside="() => { $emit('close-sign-up') }"
  >
  <v-card>
      <v-card-title>
        <span class="text-h2"> {{ currentTitle }} </span>
      </v-card-title>

      <v-window v-model="step">
        <v-window-item :value="1">
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
              >
                <v-btn
                  slot="append"
                  outlined
                  @click="checkMultiEmail"
                >
                  중복 검사
                </v-btn>
              </v-text-field>


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
          </v-container>
        </v-window-item>

        <v-window-item :value="2">
          <v-container>
            <v-text-field
              v-model="inputNumber"
              label="이메일 인증 번호 입력"
              required
            >
            <span
              slot="append"
            >
              {{ minutes }} : {{ seconds }}
            </span>
            </v-text-field>
          </v-container>
        </v-window-item>
      </v-window>

      <v-divider></v-divider>
      <v-card-actions>
        <v-btn
          :disabled="step === 1"
          text
          @click="step--"
        >
          이전
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          depressed
          color="red"
          @click="getNextStep"
        >
          <span v-show="step < 2"> 다음 </span>
          <span v-show="step === 2"> 완료 </span>
        </v-btn>
      </v-card-actions>
    </v-card>

  </v-dialog>
</template>

<script>
// import $axios from 'axios'

export default {
  name: 'SignUp',
  components:{
  },

  props:{
    open : Boolean
  },
  data(){
    return{
      step: 1,

      userData : {},
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
      ],

      confirmationNumber: '123',
      inputNumber: '',

      IsMultiEmail : false,

      timerCount: 180,
      minutes: 3,
      seconds: 30,
      timer: null,
    }
  },

  computed:{
    signUpDialog(){
      return this.open ? true : false
    },

    currentTitle(){
      switch(this.step){
        case 1: return '회원가입'
        case 2: return '이메일 본인 인증'
        default: return '회원가입 완료'
      }
    }
  },

  methods:{
    checkMultiEmail(inputEmail){
      return this.$store.dispatch('checkMultiEmail', inputEmail)
    },

    checkSignUpForm(){
      const validate =  this.$refs.form.validate()
      if (validate && this.checkMultiEmail(this.userEmail)){
        // 유호성 통과
        this.userData = {
          userName : this.userName,
          password : this.userPassword,
          userEmail : this.userEmail,
          telNum :  this.userTelNum.replaceAll('-', '')
        }
        return true
      } else{
        return false
      }
    },

    getNextStep(){
      if(this.step === 1){
        const checkResult = this.checkSignUpForm()
        if(checkResult){
          this.step += 1
        }
      } else if(this.step === 2){
        if(this.inputNumber === this.confirmationNumber){
          this.$store.dispatch('createUser', {userData: this.userData, this: this})
          alert('회원가입 성공!')
          // this.confirmationNumber = ''
          // this.inputNumber = ''
          // this.step = 1
          // this.$emit('close-sign-up')
        } else{
          alert('이메일 인증 실패')
          this.confirmationNumber = ''
          this.inputNumber = ''
          this.step = 1
        }
      }
    },

    setTimer(){
      if(this.timerCount > 0){
          this.timer = setInterval(() => {
                this.timerCount -= 1
            }, 1000)
          } 
      },
    },

    watch: {
      step: function(){
        if(this.step === 2){
          this.timerCount = 180
          this.minutes = 3
          this.seconds = 30
          this.setTimer()
        } else{
          clearInterval(this.timer)
        }
      },

      timerCount: function(){
        if(this.seconds > 0){
          this.seconds -= 1
        } else if(this.minutes > 0) {
          this.minutes -= 1
          this.seconds = 59
        } else{
          this.step = 1
        }

      }
  },

  beforeDestroy(){
    this.userData = {}
    this.userName = ''
    this.userEmail = ''
    this.userPassword = ''
    this.userPasswordConfirmation = ''
    this.userTelNum = ''

    this.confirmationNumber = ''
    this.inputNumber = ''
  }
}
</script>

<style>

</style>