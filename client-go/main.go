package main

import (
	"errors"
	"time"

	"github.com/getsentry/sentry-go"
)

func main() {
	sentry.Init(sentry.ClientOptions{
		Dsn: "http://05316d9921fd4fc19f1285bb92a09fdc@localhost:9000/3",
	})

	sentry.CaptureException(errors.New("error"))
	sentry.Flush(time.Second * 5)
}
