import {Component, OnInit} from '@angular/core';
import {User} from "../domain/User";
import {SelectItem} from "primeng/api";
import {Dropdown} from "primeng/primeng";
import {Skill} from "../domain/Skill";

@Component({
  selector: 'app-coachlist',
  templateUrl: './coachlist.component.html',
  styleUrls: ['./coachlist.component.css']
})
export class CoachlistComponent implements OnInit {
  coaches : User[];

  // coaches : User[] = [
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"},
  //   {login: "Олександра Остапенко", description: "Всем привет! Я спортивный тренер в городе Киев. " +
  //     "Мой основной профиль - занятия кроссфитом и функциональными тренировками. Так же занимаюсь " +
  //     "тренерством по бодибилдингу, провожу занятия в тренажерном зале. Работаю в залах на подоле, " +
  //     "возле станции метро Политихническая, возможны индивидуальные программы на дому либо на каком-то " +
  //     "стадионе. Я очень разговорчивый и неконфликтный человек, но настойчивый и профессиональный, " +
  //     "поэтому ваша фигура в безопасности, если она в моих руках!"}
  // ];

  categories: SelectItem[];
  shownStudies: Skill[];
  studies: Skill[];
  costs: SelectItem[];
  cities: SelectItem[];
  rangeValues: number[] = [200,500];

  selectedCategory: SelectItem;

  constructor() {
    this.categories = [
      {label: 'Все', value: 'Все'},
      {label: 'Спорт', value: 'Спорт'},
      {label: 'Программирование', value: 'Программирование'},
      {label: 'Языки', value: 'Языки'},
      {label: 'Точные науки', value: 'Точные науки'}
    ];

    // this.studies = [
    //   {categoryId: 'Спорт', name: 'Плавание'},
    //   {categoryId: 'Спорт', name: 'Теннис'},
    //   {categoryId: 'Программирование', name: 'Java'},
    //   {categoryId: 'Программирование', name: 'Проектирование решений'},
    //   {categoryId: 'Языки', name: 'Английский'},
    //   {categoryId: 'Языки', name: 'Французский'},
    //   {categoryId: 'Точные науки', name: 'Математика'},
    //   {categoryId: 'Точные науки', name: 'Физика'}
    // ];

    this.cities = [
      {label: 'Киев', value: 'Киев'},
      {label: 'Луцк', value: 'Луцк'},
      {label: 'Сумы', value: 'Сумы'},
      {label: 'Полтава', value: 'Полтава'},
      {label: 'Хмельницкий', value: 'Хмельницкий'}
    ];
  }

  ngOnInit() {
    // this.shownStudies = this.studies;
  }

  selectLesson() {
    // this.shownStudies = this.selectedCategory.label === 'Все' ? this.studies :
    //   this.studies.filter(study => study.categoryId === this.selectedCategory.label);
  }

  clearFilter(dropdown: Dropdown) {
    dropdown.resetFilter();
  }

}
