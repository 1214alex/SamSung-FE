<template>
    <div>
      <Header></Header>
      <main class="main">
        <div class="image-wrapper">
          <img
            src="@/assets/diet_main_img.jpg"
            alt="기본 이미지"
            class="main-img"
          />
          <img
            src="@/assets/diet_main_img_hover.png"
            alt="호버 이미지"
            class="hover-overlay"
          />
        </div>
      </main>
    </div>
</template>
  
<script setup>
  import Header from '@/components/common/Header.vue';
</script>
  
<style scoped>
    .main {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        position: relative;
        z-index: 1;
    }
    
    .image-wrapper {
        position: relative;
        width: 100%;
        max-width: 800px;
        aspect-ratio: 7/5; /* 이미지 비율 유지 (선택) */
        z-index: 1;
    }
    
    .main-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        display: block;
    }
    
    /* 호버 이미지: 평소엔 안 보이고, 위에 겹쳐짐 */
    .hover-overlay {
        position: absolute;
        top: 0;
        left: 0;
        opacity: 0;
        width: 100%;
        height: 100%;
        transition: opacity 0.3s ease;
        pointer-events: none;
    }

    /* 마우스를 올리면 호버 이미지가 부드럽게 나타남 */
    .image-wrapper:hover .hover-overlay {
        opacity: 0.6; /* 투명도 설정 */
    }
    header {
      z-index: 1000;
    }
</style>
  