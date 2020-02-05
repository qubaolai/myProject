import intercept from "@/utils/intercepter";

export function getLeasByConditions(data) {
  return intercept.request({
    method: "post",
    url: "/lea/getLeasByConditions",
    data: data
  });
}
