import { readUser } from "@/utils/cache";
import { Router } from "vue-router";

const whiteList = ['/login', '/register', '/forgot']

export const registerGuard = (router: Router) => {

  router.beforeEach((to, _, next) => {
    if (whiteList.includes(to.path)) {
      return next();
    }
    const user = readUser();

    if (!user.id) {
      return next("/login");
    }
    next();
  })

  router.afterEach(() => {
    console.log('afterEach')
  })

}
