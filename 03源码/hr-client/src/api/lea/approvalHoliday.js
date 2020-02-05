import intercept from "@/utils/intercepter";

export function approvalHoliday(data) {
  return intercept.request({
    method: "put",
    url: "/lea/approvalHoliday",
    data: data
  });
}
