// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';

// 페이지 컴포넌트 불러오기
import Home from '@/components/views/Home.vue';
import Regist from '@/components/Regist.vue';
import LoginModal from '@/components/LoginModal.vue';
import SubscribeList from '@/components/subscribe/SubscribeList.vue';
import BlockUserList from '@/components/block/BlockUserList.vue';
// import About from '@/pages/About.vue';
// import Contact from '@/pages/Contact.vue';


const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: Home, component: Home },
    { path: '/register', component: Regist },
    { path: '/login', component: LoginModal },


            {
                path: '/subscribeList',
                name: 'Subscribe',
                component: SubscribeList
            },
            {
                path:'/blockList',
                name: 'Block',
                component: BlockUserList
            }
  ]
});

export default router;
