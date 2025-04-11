<template>
    <header class="header">
      <div>
        <RouterLink to="/" class="logo">
          <img src="@/assets/DieatLogo2.png" alt="Dieat Main Page" />
        </RouterLink>
      </div>
  
      <nav class="main-nav">
        <div class="menu-item" @click="toggleDropdown('FOOD')">
          FOOD
          <MenuDrop :menuItems="menu1" :visible="activeMenu === 'FOOD'" />
        </div>
        <div class="menu-item" @click="toggleDropdown('MEAL')">
          MEAL
          <MenuDrop :menuItems="menu2" :visible="activeMenu === 'MEAL'" />
        </div>
        <div class="menu-item" @click="toggleDropdown('POST')">
          POST
          <MenuDrop :menuItems="menu3" :visible="activeMenu === 'POST'" />
        </div>
        <div class="menu-item" @click="toggleDropdown('MYPAGE')">
          MYPAGE
          <MenuDrop :menuItems="menu4" :visible="activeMenu === 'MYPAGE'" />
        </div>
        <div class="menu-item" @click="toggleDropdown('SERVICE')">
          SERVICE
          <MenuDrop :menuItems="menu5" :visible="activeMenu === 'SERVICE'" />
        </div>
      </nav>
  
      <nav class="auth-nav">
        <template v-if="user">
          <div class="user-box">
            <div class="user-text">
              <div class="user-name">{{ user.name }}</div>
              <div class="user-role">일반 사용자</div>
            </div>
            <button class="logout-btn" @click="logout">로그아웃</button>
          </div>
        </template>
        <template v-else>
          <button @click="emit('open-login')">로그인</button>
          <RouterLink to="/register">회원가입</RouterLink>
        </template>
      </nav>
    </header>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { RouterLink } from 'vue-router'
  import MenuDrop from './MenuDrop.vue'
  
  const emit = defineEmits(['open-login'])
  const activeMenu = ref(null)
  const user = ref(null)
  
  const toggleDropdown = (menu) => {
    activeMenu.value = activeMenu.value === menu ? null : menu
  }
  
  const logout = () => {
    localStorage.removeItem('accessToken')
    user.value = null
  }
  
  const handleLoginSuccess = async () => {
    try {
      const token = localStorage.getItem('accessToken')
      if (!token) return
      const res = await fetch('http://localhost:8000/user-service/users/me', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      if (!res.ok) throw new Error('토큰 인증 실패')
      user.value = await res.json()
    } catch (err) {
      console.error('유저 정보 불러오기 실패:', err)
      localStorage.removeItem('accessToken')
      user.value = null
    }
  }
  
  onMounted(() => {
    handleLoginSuccess()
  })
  
  // 메뉴 데이터
  const menu1 = [
    { label: '음식 데이터 검색', href: '/searchFood' },
    { label: '음식 데이터 등록', href: '/registerFood' }
  ]
  const menu2 = [
    { label: '식사 조회', href: '/readMeal' },
    { label: '식사 등록', href: '/registerMeal' },
    { label: '식단 게시글 조회', href: '/readDiet' },
    { label: '식단 게시글 등록', href: '/registerDiet' }
  ]
  const menu3 = [
    { label: '자유 게시글 조회', href: '/readFree' },
    { label: '자유 게시글 등록', href: '/registerFree' },
    { label: '성공기 게시글 조회', href: '/readSuccess' },
    { label: '성공기 게시글 등록', href: '/registerSuccess' }
  ]
  const menu4 = [
    { label: '개인 정보', href: '/userInfo' },
    { label: '포인트 적립 내역', href: '/point' },
    { label: '북마크', href: '/category' },
    { label: '구독 관리', href: '/subscribeMng' },
    { label: '구독 게시판', href: '/subscribePost' },
    { label: '차단', href: '/block' }
  ]
  const menu5 = [
    { label: '공지 사항', href: '/notice' },
    { label: '문의 사항', href: '/qna' },
    { label: '나의 신고 내역', href: '/myReport' }
  ]
  </script>
  
  <style scoped>
  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #155b45;
    padding: 30px;
    z-index: 3000;
    position: relative;
  }
  
  .logo img {
    height: 50px;
    margin-left: 50px;
  }
  
  .main-nav {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    gap: 100px;
    color: white;
    font-weight: bold;
  }
  
  .menu-item {
    position: relative;
    cursor: pointer;
  }
  
  .auth-nav {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-right: 50px;
  }
  
  .auth-nav a,
  .auth-nav button {
    height: 50px;
    color: white;
    text-decoration: none;
    font-size: 15px;
    background: none;
    border: none;
    cursor: pointer;
  }
  
  .user-box {
    display: flex;
    align-items: center;
    gap: 12px;
    color: white;
  }
  
  .user-text {
    display: flex;
    flex-direction: column;
    font-size: 13px;
    line-height: 1.2;
  }
  
  .user-name {
    font-weight: bold;
  }
  
  .user-role {
    font-size: 11px;
    opacity: 0.8;
  }
  
  .logout-btn {
    background: none;
    border: none;
    color: white;
    font-size: 13px;
    cursor: pointer;
  }
  </style>
  