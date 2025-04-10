<template>
    <div class="subscription-container">
      <h2 class="title">구독 관리</h2>
      <div class="subscription-card">
        <div class="subscription-item" v-for="user in subscribers" :key="user.id">
          <span class="username">{{ user.name }}</span>
          <div class="actions">
            <button class="cancel-btn" @click="cancelSubscription(user.id, user.name)">구독 취소</button>
            <button class="view-btn">게시글 보기</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  
  const subscribers = ref([])
  
  onMounted(async () => {
    try {
      const res = await fetch('http://localhost:8080/subscribers') // public/SubscribeDB.json
      if (!res.ok) throw new Error('구독자 목록을 불러오지 못했습니다')
      subscribers.value = await res.json()
    } catch (err) {
      console.error('에러:', err)
    }
  })
  
  const cancelSubscription = (id, name) => {
    alert(`${name}님의 구독을 취소했습니다.`)
    subscribers.value = subscribers.value.filter(sub => sub.id !== id)
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
    padding: 16px 0;
    border-bottom: 1px solid #eee;
  }
  
  .subscription-item:last-child {
    border-bottom: none;
  }
  
  .username {
    font-size: 16px;
    font-weight: 500;
  }
  
  .actions {
    display: flex;
    gap: 12px;
  }
  
  .cancel-btn,
  .view-btn {
    border: 1px solid #333;
    background: white;
    padding: 6px 16px;
    border-radius: 9999px;
    font-size: 14px;
    cursor: pointer;
  }
  
  .cancel-btn:hover
   {
    background-color: red;
    color:white;
    border:none;
  }

  .view-btn:hover{
    background-color: #f2f2f2;
  }
  </style>
  