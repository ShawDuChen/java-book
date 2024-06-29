import { ref } from "vue";

export const useBool = (init = false) => {
  const bool = ref(init);

  const toggle = () => {
    bool.value = !bool.value;
  }

  return { bool, toggle }
} 
