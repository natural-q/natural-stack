module.exports = {
  name: '教务辅助管理系统',
  prefix: 'antdAdmin',
  footerText: '教务辅助管理系统  © 2017',
  logo: '/logo.png',
  iconFontCSS: '/iconfont.css',
  iconFontJS: '/iconfont.js',
  baseURL: 'http://localhost:8000/api/v1',
  YQL: ['http://leaf.qmen.space'],
  CORS: ['http://leaf.qmen.space:5000'],
  openPages: ['/login'],
  apiPrefix: '/api/v1',
  api: {
    userLogin: '/user/login',
    userLogout: '/user/logout',
    userInfo: '/userInfo',
    users: '/users',
    user: '/user/:id',
    dashboard: '/message',
  },
}
