package org.example.service.browser.chrome;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverChrome {

    public WebDriver getDriverChrome() {
        /*
        Это облегчит процесс обновления ChromeDriver и поможет вам использовать актуальную версию.
         После добавления этой зависимости вы можете использовать следующий код
         для автоматической загрузки актуальной версии ChromeDriver:
         */
        //WebDriverManager.chromedriver().setup();


        // установливаем зависимость, определяющую путь к chromedriver
        // https://googlechromelabs.github.io/chrome-for-testing/
        // https://chromedriver.chromium.org/downloads
        //String chromedriverPath = System.getProperty("user.home") + File.separator +
                //"chromedriver_win32" + File.separator + "chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver", chromedriverPath);

        //https://peter.sh/experiments/chromium-command-line-switches/
        ChromeOptions options = new ChromeOptions();
        //https://www.selenium.dev/documentation/webdriver/drivers/options/#pageloadstrategy
        options.setPageLoadStrategy(PageLoadStrategy.EAGER); // ускорение загрузки сайта
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--user-agent='Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17'");
        options.addArguments("start-maximized");

        /*
        Опция `--no-sandbox` в настройках ChromeDriver отключает использование механизма песочницы (sandbox),
        который предназначен для изоляции процессов браузера и обеспечения безопасности.
        Под песочницей в данном контексте понимается механизм, который ограничивает доступ браузера
        к ресурсам компьютера, таким как файловая система или оперативная память, в целях предотвращения
        вредоносного программного кода (например, вредоносных расширений или веб-страниц) от повреждения
        операционной системы или других процессов.
        Однако, в определенных случаях, например, при запуске ChromeDriver на Linux-системах,
        использование песочницы может вызывать проблемы и приводить к непредвиденным ошибкам.
        В таких случаях можно указать опцию `--no-sandbox`, чтобы отключить песочницу и избежать возможных проблем.
        Важно отметить, что отключение песочницы может повысить риск безопасности, поэтому
        использование опции `--no-sandbox` следует рассматривать с осторожностью и только в
        тех случаях, когда это необходимо и безопасно.
         */
        //options.addArguments("--no-sandbox"); // Отключение песочницы

        /*
        Опция `--disable-dev-shm-usage` в настройках ChromeDriver отключает
        использование "/dev/shm" для создания временных файлов в памяти.
        "/dev/shm" (или "tmpfs") — это механизм в Linux, который позволяет создавать
        файлы в оперативной памяти (RAM) вместо файловой системы. Это может быть полезным
        для повышения производительности и ускорения доступа к данным.
        Однако, по умолчанию, Chrome использует "/dev/shm" для создания своих временных файлов,
        например, для кэширования страниц. Но в некоторых случаях, особенно на серверных
        машинах или виртуальных машинах с ограниченными ресурсами памяти, размер "/dev/shm"
         может быть ограниченным, что может привести к проблемам работы браузера.
        Использование опции `--disable-dev-shm-usage` позволяет отключить использование
        "/dev/shm" в ChromeDriver, и браузер будет создавать временные файлы в обычной файловой системе.
         Это может помочь избежать проблем с ограниченным размером "/dev/shm".
        Важно отметить, что использование этой опции может повысить использование памяти,
        поскольку временные файлы будут храниться в файловой системе, а не в оперативной памяти.
         */
        //options.addArguments("--disable-dev-shm-usage"); // Отключение использования /dev/shm

        WebDriver chromeDriver = new ChromeDriver(options);

         /*
        Ожидание каждый раз когда выполняется команда на сайте
        Таким образом, если элемент не найден, то драйвер будет ждать его появления
        в течении заданного времени (10 секунд) и шагом в 500 мс.
        Как только элемент будет найден, драйвер продолжит работу, однако,
        в противном случае тест упадем по истечению времени
        https://www.selenium.dev/documentation/webdriver/waits/
         */
       //Duration duration = Duration.ofSeconds(10);
        //driver.manage().timeouts().implicitlyWait(duration);
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        return chromeDriver;
    }

}
