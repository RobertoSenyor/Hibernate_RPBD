import service.HomeService;

public class Main
{


    public static void main(String[] args)
    {
        HomeService homeService = new HomeService();

        System.out.println(homeService.findHomeById(1));

        System.out.println("Hello world!");
    }
}