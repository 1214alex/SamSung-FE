<template>
    <div class="form-group">
      <label class="form-label">{{ label }}</label>
      <input
        :type="type"
        :placeholder="placeholder"
        class="form-input"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
      />
    </div>
  </template>
  
  <script setup>
  const props = defineProps({
    label: String,
    placeholder: String,
    type: {
      type: String,
      default: 'text'
    },
    modelValue: String
  })
  const emit = defineEmits(['update:modelValue'])
  </script>
  
  <style scoped>
  .form-group {
    margin-bottom: 16px;
  }
  .form-label {
    display: block;
    margin-bottom: 6px;
    color: #333;
    font-weight: 500;
  }
  .form-input {
    width: 100%;
    padding: 10px 12px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
    box-sizing: border-box;
  }
  </style>