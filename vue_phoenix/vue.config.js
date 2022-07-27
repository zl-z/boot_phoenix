const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  //设置端口号
  devServer:{
    port:8100,
    allowedHosts: [
      'http://7fqgi2.natappfree.cc', // 允许访问的域名地址，即花生壳内网穿透的地址
      '.natappfree.cc'   // .是二级域名的通配符
    ],
    //proxy内的代码
    /*proxy: {
      // 第一台服务器配置
      '/gosd': {
        target: process.env.SUPERDOCK_API_SERVER || 'https://!***.***.*.***:8080',//这里填写项目真实的后台接口地址
        pathRewrite: { '^/gosd':  '' },
        changeOrigin: true //设置允许跨域
      },
      // 第二台服务器配置
      '/intellcampus': {
        target: 'https://49.4.31.193:8080',//这里填写项目真实的后台接口地址
        //这个重写不可省略！因为我们真正请求的地址并不含 /watwise_war
        // pathRewrite: { '^/watwise_war':  '' },
        //这个重写不可省略！因为我们真正请求的地址包含 /watwise_war
        pathRewrite: { '/intellcampus':  '/intellcampus' },
        changeOrigin: true //设置允许跨域
      }
    }*/
  }
})
