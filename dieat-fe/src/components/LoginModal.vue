<template>
    <div class="login-modal-overlay" v-if="show">
      <div class="login-modal-box">
        <h2 class="login-title">Login</h2>
        <input type="text" placeholder="아이디" class="login-input" v-model="id" />
        <input type="password" placeholder="비밀번호" class="login-input" v-model="password" />
        <button class="login-button" @click="$emit('login', { id, password })">로그인</button>
        <img src="../img/dieat_greenLogo 1.svg" alt="logo" class="login-logo" />
        <a class="signup-text" href="/register">Sign up</a>
      </div>
    </div>
  </template>
  
  <script setup>
  const props = defineProps({
    show: Boolean
  })
  const emit = defineEmits(['login'])
  
  import { ref } from 'vue'
  const id = ref('')
  const password = ref('')
  </script>
  
  <style scoped>
  .login-modal-overlay {
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  .login-modal-box {
    background: #fff;
    border-radius: 16px;
    padding: 40px 30px;
    width: 320px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  .login-title {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 20px;
    font-family: 'cursive';
  }
  .login-input {
    width: 100%;
    padding: 10px 14px;
    margin-bottom: 14px;
    border: 1px solid #ccc;
    border-radius: 12px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    font-size: 14px;
  }
  .login-button {
    width: 100%;
    padding: 10px;
    background-color: #11875c;
    color: #fff;
    font-weight: bold;
    font-size: 16px;
    border: none;
    border-radius: 30px;
    cursor: pointer;
    margin-bottom: 24px;
  }
  .login-logo {
    width: 120px;
    margin-bottom: 12px;
  }
  .signup-text {
    font-size: 16px;
    font-weight: bold;
    font-family: 'cursive';
  }
  </style>
  