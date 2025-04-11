<template>
    <div class="form-container">
      <h1 class="form-title">회원가입</h1>
  
      <FormInput label="아이디" placeholder="아이디 입력" v-model="form.id" />
      <p v-if="formError.id" class="error-message">아이디는 필수 입력 항목입니다.</p>
  
      <FormInput label="비밀번호" placeholder="비밀번호 입력" type="password" v-model="form.password" />
      <p v-if="formError.password" class="error-message">비밀번호는 필수 입력 항목입니다.</p>
  
      <FormInput label="비밀번호 확인" placeholder="비밀번호 입력" type="password" v-model="form.passwordCheck" />
      <p v-if="formError.passwordCheck" class="error-message">비밀번호 확인은 필수 입력 항목입니다.</p>
  
      <p
        class="match-message"
        v-if="form.passwordCheck"
        :class="{ success: isPasswordMatch, error: !isPasswordMatch }"
      >
        {{ isPasswordMatch ? '비밀번호가 일치합니다.' : '비밀번호가 일치하지 않습니다.' }}
      </p>
  
      <FormInput label="이름" placeholder="이름을 입력해주세요" v-model="form.name" />
      <p v-if="formError.name" class="error-message">이름은 필수 입력 항목입니다.</p>
  
      <FormInput label="닉네임" placeholder="닉네임을 입력해주세요" v-model="form.nickname" />
      <p v-if="formError.nickname" class="error-message">닉네임은 필수 입력 항목입니다.</p>
  
      <div class="form-group">
        <label class="form-label">이메일</label>
        <div class="email-row">
          <input v-model="form.emailId" placeholder="이메일 주소" class="form-input" />
          <span>@</span>
          <select v-model="form.emailDomain" class="form-select">
            <option value="naver.com">naver.com</option>
            <option value="gmail.com">gmail.com</option>
            <option value="daum.net">daum.net</option>
          </select>
        </div>
      </div>
  
      <FormInput label="전화번호" placeholder="전화번호를 입력해주세요" v-model="form.phone" />
      <p v-if="formError.phone" class="error-message">전화번호는 필수 입력 항목입니다.</p>
  
      <div class="form-group">
        <label class="form-label">성별</label>
        <div class="gender-row">
          <button :class="['gender-btn', form.gender === 'male' ? 'active' : '']" @click="form.gender = 'male'">남</button>
          <button :class="['gender-btn', form.gender === 'female' ? 'active' : '']" @click="form.gender = 'female'">여</button>
        </div>
      </div>
  
      <div class="form-group">
        <label class="form-label">생일</label>
        <div class="birth-row">
          <select v-model="form.birthYear" class="form-select">
            <option disabled>년도</option>
            <option v-for="year in years" :key="year" :value="year">{{ year }}</option>
          </select>
          <select v-model="form.birthMonth" class="form-select">
            <option disabled>월</option>
            <option v-for="month in 12" :key="month" :value="month">{{ month }}</option>
          </select>
          <select v-model="form.birthDay" class="form-select">    <!-- 프론트 fetch를 통해 year,month,day를 문자열로 합쳐서 백으로 전송-->
            <option disabled>일</option>
            <option v-for="day in 31" :key="day" :value="day">{{ day }}</option>
          </select>
        </div>
      </div>
  
      <FormInput label="관리자 코드" placeholder="관리자 코드를 입력해주세요" v-model="form.adminCode" />
  
      <div class="form-actions">
        <button class="submit-btn" @click="validateForm">가입하기</button>
        <button class="cancel-btn" @click="isModalClose = true">가입취소</button>
      </div>
  
      <RegistCompleteModal
        :show="isModalOpen"
        @close="isModalOpen = false"
        icon="✔"
        title="회원가입 완료"
        message="지금 바로 다양한 콘텐츠를 감상하고 글을 작성해 보세요"
        color="#22611d"
      />
  
      <RegistCompleteModal
        :show="isModalClose"
        @close="isModalClose = false"
        icon="X"
        title="가입 취소"
        message="가입을 취소하고 다시 로그인 페이지로 돌아갑니다"
        color="#FE7C7C"
      />
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  import FormInput from '@/components/FormInput.vue';
  import RegistCompleteModal from '@/components/RegistCompleteModal.vue';
  
  const form = ref({
    id: '',
    password: '',
    passwordCheck: '',
    name: '',
    nickname: '',
    emailId: '',
    emailDomain: 'naver.com',
    phone: '',
    gender: '',
    birthYear: '',
    birthMonth: '',
    birthDay: '',
    adminCode: ''
  })
  
  const formError = ref({
    id: false,
    password: false,
    passwordCheck: false,
    name: false,
    nickname: false,
    phone: false
  })
  
  const isModalOpen = ref(false)
  const isModalClose = ref(false)
  
  const years = Array.from({ length: 100 }, (_, i) => 2025 - i)
  
  const isPasswordMatch = computed(() => {
    return form.value.password && form.value.password === form.value.passwordCheck
  })
  
  const validateForm = () => {
    formError.value.id = !form.value.id
    formError.value.password = !form.value.password
    formError.value.passwordCheck = !form.value.passwordCheck
    formError.value.name = !form.value.name
    formError.value.nickname = !form.value.nickname
    formError.value.phone = !form.value.phone
  
    const isValid = Object.values(formError.value).every(v => v === false)
    if (isValid && isPasswordMatch.value) {
      isModalOpen.value = true
    }
  }
  </script>

<style scoped>
.form-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 48px 16px;
  font-family: sans-serif;
}
.form-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 32px;
}
.form-group {
  margin-bottom: 16px;
}
.form-label {
  display: block;
  margin-bottom: 6px;
  color: #333;
  font-weight: 500;
}
.form-input,
.form-select {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 14px;
}
.email-row {
  display: flex;
  align-items: center;
  gap: 8px;
}
.gender-row {
  display: flex;
  gap: 12px;
}
.gender-btn {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background: #fff;
  cursor: pointer;
}
.gender-btn.active {
  background: #28a745;
  color: white;
  border-color: #28a745;
}
.birth-row {
  display: flex;
  gap: 8px;
}
.form-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 32px;
  gap: 12px;
}
.submit-btn {
  flex: 1;
  padding: 12px;
  background-color: #119160;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
}
.cancel-btn {
  flex: 1;
  padding: 12px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
}
.match-message {
  margin-top: 6px;
  font-size: 14px;
}
.match-message.success {
  color: green;
}
.match-message.error {
  color: red;
}
.error-message {
  color: red;
  font-size: 13px;
  margin-top: -10px;
  margin-bottom: 10px;
}

</style>
