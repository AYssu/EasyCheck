import { ref, computed } from 'vue';

let listeners = 0; // 用于跟踪监听器的数量
const screenWidth = ref(window.innerWidth);
const phone_bool = computed(() => screenWidth.value < 1200);

const updateScreenWidth = () => {
    screenWidth.value = window.innerWidth;
};

export default function phone_size() {
    // 增加监听器计数
    listeners++;

    // 如果是第一个监听器，则添加事件监听
    if (listeners === 1) {
        console.log("add_phone_size listeners")
        window.addEventListener('resize', updateScreenWidth);
    }

    // 如果是最后一个监听器，则移除事件监听
    const remove_phone_size = () => {
        listeners--;
        if (listeners === 0) {
            console.log("remove_phone_size listeners")
            window.removeEventListener('resize', updateScreenWidth);
        }
    };

    return { phone_bool, remove_phone_size };
}