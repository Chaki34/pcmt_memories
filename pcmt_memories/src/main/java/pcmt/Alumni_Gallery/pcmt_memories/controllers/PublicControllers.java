package pcmt.Alumni_Gallery.pcmt_memories.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller

public class PublicControllers {

    @GetMapping("/")
    public String  Test (){
        return "home";
    }
    
    @GetMapping("/alumni-search")
    public String  aluminiSearch(){
    return  "alumni-search";
    }

    @GetMapping("/result")
    public String searchresult(Model model) {
        List<Alumnus> alumni = new ArrayList<>();

        // Demo Images from your provided text
        String[] images = {
                "https://assets.codepen.io/215059/photo-1594149221886-eb520f85ad25.jpg",
                "https://assets.codepen.io/215059/photo-1589156191108-c762ff4b96ab.jpg",
                "https://assets.codepen.io/215059/photo-1488197047962-b48492212cda.jpg",
                "https://assets.codepen.io/215059/photo-1469565686301-a0cf06bacf92.jpg",
                "https://assets.codepen.io/215059/photo-1559827291-72ee739d0d9a.jpg",

                "https://images.unsplash.com/photo-1500648767791-00dcc994a43e",
                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1",
                "https://images.unsplash.com/photo-1517841905240-472988babdf9",
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d",
                "https://images.unsplash.com/photo-1544723795-3fb6469f5b39",

                "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e",
                "https://images.unsplash.com/photo-1519345182560-3f2917c472ef",
                "https://images.unsplash.com/photo-1502767089025-6572583495b0",
                "https://images.unsplash.com/photo-1527980965255-d3b416303d12",
                "https://images.unsplash.com/photo-1524502397800-2eeaad7c3fe5",

                "https://images.unsplash.com/photo-1521119989659-a83eee488004",
                "https://images.unsplash.com/photo-1504593811423-6dd665756598",
                "https://images.unsplash.com/photo-1511367461989-f85a21fda167",
                "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde",
                "https://images.unsplash.com/photo-1519340241574-2cec6aef0c01",

                "https://images.unsplash.com/photo-1534528741775-53994a69daeb",
                "https://images.unsplash.com/photo-1500648767791-00dcc994a43e",
                "https://images.unsplash.com/photo-1524503033411-f9f5f6a1b1f1",
                "https://images.unsplash.com/photo-1502323777036-f29e3972d82f",
                "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6",

                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1",
                "https://images.unsplash.com/photo-1521119989659-a83eee488004",
                "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e",
                "https://images.unsplash.com/photo-1517841905240-472988babdf9",
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d",

                "https://images.unsplash.com/photo-1544723795-3fb6469f5b39",
                "https://images.unsplash.com/photo-1519345182560-3f2917c472ef",
                "https://images.unsplash.com/photo-1502767089025-6572583495b0",
                "https://images.unsplash.com/photo-1527980965255-d3b416303d12",
                "https://images.unsplash.com/photo-1524502397800-2eeaad7c3fe5",

                "https://images.unsplash.com/photo-1504593811423-6dd665756598",
                "https://images.unsplash.com/photo-1511367461989-f85a21fda167",
                "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde",
                "https://images.unsplash.com/photo-1519340241574-2cec6aef0c01",
                "https://images.unsplash.com/photo-1534528741775-53994a69daeb",

                "https://images.unsplash.com/photo-1524503033411-f9f5f6a1b1f1",
                "https://images.unsplash.com/photo-1502323777036-f29e3972d82f",
                "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6",
                "https://images.unsplash.com/photo-1500648767791-00dcc994a43e",
                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1"
        };

        // Generating a grid pattern similar to your demo data
        // X ranges from -37 to 35, Y ranges from -4 to 5
        int imgIndex = 0;
        for (int x = -37; x <= 35; x += 2) {
            for (int y = (x % 4 == 0 ? -4 : -3); y <= 5; y += 2) {
                String img = images[imgIndex % images.length];
                alumni.add(new Alumnus(img, x, y));
                imgIndex++;
            }
        }

        model.addAttribute("alumni", alumni);
        return "search-result";
    }

    // Simple Inner Class for Demo
    public static class Alumnus {
        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String imageUrl;

        public int getGridX() {
            return gridX;
        }

        public void setGridX(int gridX) {
            this.gridX = gridX;
        }

        private int gridX;

        public int getGridY() {
            return gridY;
        }

        public void setGridY(int gridY) {
            this.gridY = gridY;
        }

        private int gridY;

        public Alumnus(String imageUrl, int gridX, int gridY) {
            this.imageUrl = imageUrl;
            this.gridX = gridX;
            this.gridY = gridY;
        }

    }


}
