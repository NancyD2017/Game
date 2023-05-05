package org.example.Model;

class Evo_Forward1 extends EvolutionCards{
    Evo_Forward1(Model model, Player player){
        super(model,player);
    }
    @Override
    boolean action() {
        System.out.println("Можете выбрать 2 любые карты ресурсов. Введите их без пробелов: ");
        String iReceive = model.in.next();
        String[] ir = iReceive.split("");
        if (model.checkInsExchange(ir) && ir.length == 2) {
            for (String item : ir) player.cards.add(item.charAt(0));
            System.out.println("Ваши ресурсы: " + player.cards);
            return true;
        } else System.out.println("Вы ввели карты ресурсов неправильно");
        return false;
    }
}
