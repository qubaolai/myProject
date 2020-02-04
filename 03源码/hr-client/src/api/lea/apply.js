import intercept from "@/utils/intercepter";

export function apply(data) {
  return intercept.request({
    method: "post",
    url: "/lea/applyHoliday",
    data: data
  });
}
