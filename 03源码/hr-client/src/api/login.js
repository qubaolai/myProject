import intercept from "@/utils/intercepter";
/**
 * 获取验证码
 */

/**
 * 登录
 */
export function login(formData) {
  return intercept.request({
    method: "post",
    url: "/user/login",
    data: {
      employeeNumber: formData.username,
      password: formData.password
    },
    headers: {
      "Content-Type": "application/json"
    }
  });
}
/**
 * 注册
 */
