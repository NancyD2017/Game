package org.example.Model;

class Evo_Forward1 {
    boolean main(Player player) {
        Model m = new Model();
        System.out.println("Можете выбрать 2 любые карты ресурсов: ");
        String iReceive = m.in.next();
        String[] ir = iReceive.split("");
        if (m.checkInsExchange(ir) && ir.length == 2) {
            for (String item : ir) player.cards.add(item.charAt(0));
            System.out.println("Ваши ресурсы: " + player.cards);
            return true;
        } else System.out.println("Вы ввели карты ресурсов неправильно");
        return false;
    }
}
