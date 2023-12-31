import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page_object_model.MainPageModel;
import page_object_model.OrderPageModel;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class Order2ButtonsTest extends BaseTest {

    private final By orderButtonLocator;
    private final String orderPageUrl;
    // Наверху кнопка "Заказать"
    private static final By HEADER_ORDER_BUTTON = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text() ='Заказать']");
    // Внизу кнопка "Заказать"
    private static final By MIDDLE_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() ='Заказать']");
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    public Order2ButtonsTest(By orderButtonLocator, String orderPageUrl) {
        this.orderButtonLocator = orderButtonLocator;
        this.orderPageUrl = orderPageUrl;
    }
    // Тестовые данные - 2 кнопка Заказать наверху и посередине страницы
    // и адрес страницы формы заказа, чтобы проверить, осуществился ли переход
    @Parameterized.Parameters
    public static Object[][] getExpectedButton() {
        return new Object[][]{
                {HEADER_ORDER_BUTTON, ORDER_PAGE_URL},
                {MIDDLE_ORDER_BUTTON, ORDER_PAGE_URL},
        };
    }


        @Test
        public void shouldClickOnAnyOrderButton() {
            MainPageModel objPage = new MainPageModel(driver);
            objPage.open();
            objPage.acceptCookiesOnMainPage();
            OrderPageModel objOrderPage = new OrderPageModel(driver);
            // Кликаем кнопку Заказать (верхнюю или посередине- в зависимости от входящего значения из Параметров)
            objOrderPage.clickOrderButton(orderButtonLocator);
            // Получаем текущий Url, куда перешли после клика, и сравниваем с ожидаемым
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, orderPageUrl);
        }


}

