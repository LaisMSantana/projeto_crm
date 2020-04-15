import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatorioCategoriaComponent } from './relatorio-categoria.component';

describe('RelatorioCategoriaComponent', () => {
  let component: RelatorioCategoriaComponent;
  let fixture: ComponentFixture<RelatorioCategoriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelatorioCategoriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelatorioCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
