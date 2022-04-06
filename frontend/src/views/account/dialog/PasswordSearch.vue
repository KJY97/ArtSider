<template>
  <v-dialog
  :value="passwordSearchDialog"
  max-width="500px"
  persistent
  >
    <v-card style="padding: 3rem;">
      <v-card-title class="d-flex justify-center">
        <span class="text-h3" > 비밀번호 찾기 </span>        
      </v-card-title>

      <v-container style="padding-bottom:50px">
        <span class="d-flex justify-center" style="font-weight: 600; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;"> 가입하신 이메일을 입력하세요 </span>
        <v-form
          ref="form"
        >
          <v-text-field
            v-model="userEmail"
            :rules="userEmailRules"
            label="이메일"
            required
            style="width:400px;"
          >
          
          </v-text-field>
        </v-form>


        <v-row class="mt-5 px-3 justify-space-between">
          <v-btn
            text
            @click="() => { userEmail='', $emit('close-password-search') }"
          >
            닫기
          </v-btn>
          <v-btn
            text
            @click="getNewPassword"
          >
            임시 비밀번호 발급
          </v-btn>
        </v-row>
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'PasswordSearch',
  props:{
    open: Boolean
  },
  data(){
    return{
      userEmail: '',
      userEmailRules: [
        v => v.trim() !== '' || '이메일을 입력해주세요.',
        v => /^([\w._-])*[a-zA-Z0-9]+([\w._-])*([a-zA-Z0-9])+([\w._-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,}$/.test(v) || '유효한 이메일을 입력해주세요.'
      ],
    }
  },

  methods:{
    getNewPassword(){
      const validate = this.$refs.form.validate()
      if(validate){  
        // 유효성 통과
        this.userEmail = ''
        alert('입력하신 이메일로 임시 비밀번호가 발급되었습니다.\n 비밀번호를 반드시 변경해주세요.')
        this.$emit('close-password-search')
      }
    }
  },

  computed:{
    passwordSearchDialog(){
      return this.open ? true : false
    }
  }
}
</script>

<style>

</style>