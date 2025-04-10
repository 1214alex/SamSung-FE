<template>
    <div class="subscription-container">
      <h2 class="title">차단한 사람 목록</h2>
      <div class="subscription-card">
        <div class="subscription-item" v-for="user in blockedUsers" :key="user.id">
          <div class="block-info">
            <span class="username">{{ user.name }}</span>
            <span class="description">이 사람이 작성한 글이 모두 차단됨(댓글 포함)</span>
          </div>
          <button class="unblock-btn" @click="unblockUser(user.id, user.name)">차단 해제</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  
  const blockedUsers = ref([])
  const API_URL = 'http://localhost:8080/blockedUsers'
  
  onMounted(async () => {
    try {
      const res = await fetch(API_URL)
      if (!res.ok) throw new Error('차단 목록을 불러오지 못했습니다')
      blockedUsers.value = await res.json()
    } catch (err) {
      console.error('에러:', err)
    }
  })
  
  const unblockUser = async (id, name) => {
    try {
      await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
      })
      blockedUsers.value = blockedUsers.value.filter(user => user.id !== id)
      alert(`${name}님의 차단을 해제했습니다.`)
    } catch (err) {
      console.error('차단 해제 실패:', err)
    }
  }
  </script>
  
  <style scoped>
  .subscription-container {
    max-width: 700px;
    margin: 80px auto;
    padding: 20px;
    text-align: center;
  }
  
  .title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 40px;
  }
  
  .subscription-card {
    background: white;
    padding: 40px;
    border-radius: 16px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  
  .subscription-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 0;
    border-bottom: 1px solid #eee;
  }
  
  .subscription-item:last-child {
    border-bottom: none;
  }
  
  .block-info {
    text-align: left;
  }
  
  .username {
    font-size: 16px;
    font-weight: bold;
    display: block;
    margin-bottom: 4px;
  }
  
  .description {
    font-size: 13px;
    color: #555;
  }
  
  .unblock-btn {
    border: 1px solid #333;
    background: white;
    padding: 6px 16px;
    border-radius: 9999px;
    font-size: 14px;
    cursor: pointer;
  }
  
  .unblock-btn:hover {
    background-color: #f2f2f2;
  }
  </style>
  