package main;

/**
 *
 * @author dejan
 */
public class Main {

    public static void main(String[] args) {
        Select select = new Select();

        Splash splash = new Splash();
        splash.setVisible(true);

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(20);
                splash.progressNumber.setText(Integer.toString(i) + "%");
                splash.progressBar.setValue(i);
                if (i == 100) {
                    splash.setVisible(false);
                    Thread.sleep(100);
                    select.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
