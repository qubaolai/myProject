const path = require("path");
module.exports = {
  publicPath: "./", //基本路径
  //publicPath: process.env.NODE_ENV !== 'production' ? process.env.VUE_APP_URL : '/', //基本路径
  outputDir: process.env.NODE_ENV === "production" ? "dist" : "devdist", //生产环境构建输出目录
  //outputDir: process.env.VUE_APP_outputDir
  assetsDir: "", //放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录
  lintOnSave: true, // 是否开启eslint在保存的时候检查 ，false不开启
  devServer: {
    open: false, // 启动服务后是否自动打开浏览器，true-每次启动都会打开新的
    host: "0.0.0.0", // 允许外部ip访问
    port: 8081, // 端口
    https: false, // 是否启用https
    proxy: {
      "/api": {
        target: "http://39.106.29.131:8080/", //设置你调用的接口域名和端口号
        // target: "http://localhost:8080/",
        changeOrigin: true, //跨域
        pathRewrite: {
          "^/api": "/"
        }
      }
    }
  },
  css: {
    extract: true, // 是否使用css分离插件 ExtractTextPlugin。Default: 生产环境下是 true，开发环境下是 false
    sourceMap: false, // 开启 CSS source maps?
    requireModuleExtension: true, //是否开启css-modules模式, 默认值为flase
    loaderOptions: {
      //css预设器配置项
      sass: {
        // @/ 是 src/ 的别名
        prependData: `@import "src/styles/main.scss";` // 向所有 Sass 样式传入共享的全局变量
      }
    }
  },
  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]",
        include: ["./src/icons"]
      });
  },
  //判断不同环境下使用不同配置
  configureWebpack: config => {
    config.resolve = {
      // 配置路径别名
      extensions: [".js", ".json", ".vue"], // 自动添加文件名后缀
      alias: {
        vue: "vue/dist/vue.js",
        "@": path.resolve(__dirname, "./src"),
        "@c": path.resolve(__dirname, "./src/components")
      }
    };
  }
};
