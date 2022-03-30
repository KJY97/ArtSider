<template>
  <v-dialog
  :value="passwordSearchDialog"
  dark
  max-width="500px"
  persistent
  >
    <v-card>
      <v-card-title>
        <span class="text-h2" > 비밀번호 찾기 </span>        
      </v-card-title>

      <v-container>
        <v-form
          ref="form"
        >
          <v-text-field
            v-model="userEmail"
            :rules="userEmailRules"
            label="이메일"
            required
          >
          
          </v-text-field>
        </v-form>


        <v-row class="mt-5 px-3 justify-space-between">
          <v-btn
            @click="() => { userEmail='', $emit('close-password-search') }"
          >
            닫기
          </v-btn>
          <v-btn
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