import Vue from 'vue'
import VueRouter from 'vue-router'
//引入Login组件
import Login from '../components/Login.vue'
import Register from "../components/Register";
import apt from "../components/Admin/ProductManage/ProductTypeManage";
import aet from "../components/Admin/EquipmentManage/EquipmentTypeManage"
import ap from "../components/Admin/ProductManage/ProductManage"
import af from "../components/Admin/FactoryManage/FactoryManage"
import au from "../components/Admin/UserManage/UserManage"
import ae from "../components/Admin/EquipmentManage/EquipmentManage"
import me from "../components/Manager/EquipmentManage/EquipmentManage"
import ao from "../components/Admin/OrderManage/OrderManage"
import dom from "../components/Dealer/OrderManage/OrderManage"
import mo from "../components/Manager/OrderManage/TakeOrder"
import ms from "../components/Manager/OrderManage/Schedule"
Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    redirect: "/login"
  },


  {
    path: '/login',
    component: Login
  },{
    path:'/register',
    component:Register,


  },

  {
    path:'/apt',
    component:apt,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {

      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/aet',
    component:aet,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/ae',
    component:ae,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/af',
    component:af,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  }, {
    path:'/ap',
    component:ap,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/au',
    component:au,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/ao',
    component:ao,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {

      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/do',
    component:dom,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {

      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 3) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/admin',
    component: () =>import('../components/Admin/A-Home.vue'),
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)
        if (user.user_role===1) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }


  },{
    path:'/dealer',
    component: () =>import('../components/Dealer/D-Home.vue'),
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 3) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },{
    path:'/manager',
    component: () =>import('../components/Manager/M-Home'),
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 2) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }

  },
  {
    path:'/me',
    component:me,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 2) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }
  },
  {
    path:'/mtk',
    component:mo,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 2) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }
  },{
    path:'/ms',
    component:ms,
    meta: {
      requireAuth: true
    },
    beforeEnter: (to, from, next) => {
      if (to.meta.requireAuth) {
        let str = sessionStorage.getItem('user');
        let user = JSON.parse(str)

        if (user.user_role === 2) {
          next()
        } else {
          next({
            path: '/login',

          })
        }
      }
    }
  }

]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
