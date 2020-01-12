import intercept from "@/utils/intercepter";

export function initPage() {
  return intercept.request({
    method: "get",
    url: "/initPage/init"
  });
}
