<template>
  <div>
    <!-- 헤더에서 로그인 버튼 누르면 모달 열기 -->
    <TheHeader
      :user="user"
      @open-login="isLoginModalOpen = true"
      @logout="handleLogout"
    />

    <!-- 메인 화면 -->
    <RouterView />

    <!-- 로그인 모달 -->
    <LoginModal
      :show="isLoginModalOpen"
      @close="isLoginModalOpen = false"
      @login-success="handleLoginSuccess"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { RouterView } from 'vue-router'
import TheHeader from '@/components/common/Header.vue'
import LoginModal from '@/components/LoginModal.vue'

const isLoginModalOpen = ref(false)
const user = ref(null)

const handleLoginSuccess = async () => {
  try {
    const token = localStorage.getItem('accessToken')
    const res = await fetch('http://localhost:8000/user-service/users/me', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    if (!res.ok) throw new Error('인증 실패')
    user.value = await res.json()
    isLoginModalOpen.value = false
  } catch (err) {
    console.error('로그인 후 유저 정보 불러오기 실패:', err)
    localStorage.removeItem('accessToken')
    user.value = null
  }
}

const handleLogout = () => {
  localStorage.removeItem('accessToken')
  user.value = null
}

onMounted(() => {
  const token = localStorage.getItem('accessToken')
  if (token) handleLoginSuccess()
})
</script>

<style>
html, body, #app {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
