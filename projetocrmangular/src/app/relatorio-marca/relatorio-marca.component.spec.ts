import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatorioMarcaComponent } from './relatorio-marca.component';

describe('RelatorioMarcaComponent', () => {
  let component: RelatorioMarcaComponent;
  let fixture: ComponentFixture<RelatorioMarcaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelatorioMarcaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelatorioMarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
