import intercept from "@/utils/intercepter";

export function singin() {
  return intercept.request({
    method: "get",
    url: "/attendance/workSingIn"
  });
}
