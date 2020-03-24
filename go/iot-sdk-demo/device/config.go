package device

import (
	"os"
)

var (
	// 接入点
	endpoint        = os.Getenv("IOT_TEST_ENDPOINT")
	accessKeyID     = os.Getenv("IOT_TEST_ACCESS_KEY_ID")
	accessKeySecret = os.Getenv("IOT_TEST_ACCESS_KEY_SECRET")

	// 三元组
	productKey = os.Getenv("IOT_TEST_PRODUCT_KEY")
)
